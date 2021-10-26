package cn.ykf.limiter;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/26
 */
public class TokenBucketLimiter implements Limiter {

    /** 默认每秒限制数 */
    private static final int DEFAULT_RATE_LIMIT_PER_SECOND = Integer.MAX_VALUE;
    /** 每秒等于的纳秒数 */
    private static final long NANOSECONDS = 1000 * 1000 * 1000;
    /** 令牌 */
    private static final Object TOKEN = new Object();
    /** 令牌桶 */
    private final BlockingQueue<Object> bucket;
    /** 上一次定时任务执行时间，也就是放入令牌时间 */
    private long lastNanoSeconds;

    public TokenBucketLimiter() {
        this(DEFAULT_RATE_LIMIT_PER_SECOND);
    }

    public TokenBucketLimiter(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit less than 0.");
        }

        this.lastNanoSeconds = System.nanoTime();
        this.bucket = new LinkedBlockingQueue<>(limit);
        // 放入令牌的频率
        long interval = NANOSECONDS / limit;

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            long cur = System.nanoTime();
            if (cur - this.lastNanoSeconds >= interval) {
                // 放入令牌，不管入队是否成功，队列满说明请求速率没有达到限流值
                boolean flag = bucket.offer(TOKEN);
                this.lastNanoSeconds = cur;
                System.out.printf("Schedule Thread: nano: %d, put token %s.%n", cur, flag ? "success" : "failure");
            }

        }, 1000, 1000, TimeUnit.NANOSECONDS);
    }

    @Override
    public void acquire() {
        // 获取令牌，没获取到令牌的话说明限流了
        Optional.ofNullable(this.bucket.poll())
                .orElseThrow(() -> new RuntimeException("limited..."));
    }

    public static void main(String[] args) throws InterruptedException {
        Limiter rateLimiter = new TokenBucketLimiter(10);

        Runnable runnable = () -> {
            int num = 100;
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

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.submit(runnable);
        }
        threadPool.awaitTermination(100, TimeUnit.SECONDS);
    }
}
