package cn.ykf.limiter;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 漏桶算法
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/26
 */
public class LeakyBucketLimiter implements Limiter {

    /** 默认每秒限制数 */
    private static final int DEFAULT_RATE_LIMIT_PER_SECOND = Integer.MAX_VALUE;
    /** 每秒等于的纳秒数 */
    private static final long NANOSECONDS = 1000 * 1000 * 1000;
    /** 用于将请求排队的阻塞队列 */
    private final BlockingQueue<Thread> bucket;
    /** 上一次定时任务执行时间，也就是请求放行时间 */
    private long lastNanoSeconds;

    public LeakyBucketLimiter() {
        this(DEFAULT_RATE_LIMIT_PER_SECOND);
    }

    public LeakyBucketLimiter(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit less than 0.");
        }

        this.bucket = new LinkedBlockingQueue<>(limit);
        this.lastNanoSeconds = System.nanoTime();
        // 每个请求的间隔时间
        final long interval = NANOSECONDS / limit;

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // 每1k纳秒执行一次定时任务
        executor.scheduleWithFixedDelay(() -> {
            // 当前纳秒数
            long cur = System.nanoTime();
            // 如果达到间隔时间了，那就可以放行请求，使请求以一定速率执行
            if (cur - this.lastNanoSeconds >= interval) {
                // 出队，如果有待放行的请求，唤醒它
                Optional.ofNullable(this.bucket.poll()).ifPresent(thread -> {
                    LockSupport.unpark(thread);
                    System.out.printf("Schedule Thread: nano: %d, unpark thread [%s].%n", cur, thread.getName());
                });
                // 更新放行时间
                this.lastNanoSeconds = cur;
            }

        }, 1000, 1000, TimeUnit.NANOSECONDS);
    }

    @Override
    public void acquire() {
        // todo 是否可以考虑队列动态调整大小，从而动态调整限流阈值，要注意put里面的阻塞坑
        if (this.bucket.remainingCapacity() == 0) {
            // 如果队列满了，说明请求速率大于放行速率，限流
            throw new RuntimeException("limited...");
        }

        // 将当前线程入队
        Thread thread = Thread.currentThread();
        // 入队成功再让线程休眠，避免并发问题
        if (!this.bucket.offer(thread)) {
            throw new RuntimeException("limited...");
        }

        // 等待定时任务按一定速率执行
        LockSupport.park(thread);
    }


    public static void main(String[] args) throws InterruptedException {
        // 每秒20个请求
        Limiter rateLimiter = new LeakyBucketLimiter(20);

        Runnable runnable = () -> {
            int num = 10;
            while (num > 0) {
                try {
                    rateLimiter.acquire();
                } catch (Exception e) {
                    continue;
                }

                num--;
                System.out.println("Thread: " + Thread.currentThread().getName() + ", sec: " + System.currentTimeMillis() / 1000L + ", mil: " + System.currentTimeMillis() + " got a token");
            }
        };

        long start = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.submit(runnable);
        }
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        System.out.println("over time: " + (end - start) / 1000);
    }
}
