package cn.ykf.limiter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器限流
 *
 * @author YuKaiFan
 * @date 2021/10/26
 */
public class CounterLimiter implements Limiter {

    private final int limit;
    private final AtomicInteger counter;

    public CounterLimiter() {
        this(Integer.MAX_VALUE);
    }

    public CounterLimiter(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit less than 0.");
        }

        this.limit = limit;
        this.counter = new AtomicInteger();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            System.out.printf("Reset Thread: sec: %d, mil: %d, current number of request is %d.%n",
                    currentTimeMillis / 1000L, currentTimeMillis, this.counter.get());
            // 每秒清空一次计数器
            this.counter.set(0);
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void acquire() {
        // 限流策略：每秒请求不超过固定值
        if (counter.incrementAndGet() > this.limit) {
            throw new RuntimeException("limited...");
        }
    }


    public static void main(String[] args) {
        // 测试100个请求，每秒限流10个
        Limiter limiter = new CounterLimiter(10);

        int num = 100;
        while (num > 0) {
            try {
                limiter.acquire();
            } catch (Exception e) {
                continue;
            }

            num--;
            System.out.println("sec: " + System.currentTimeMillis() / 1000L + ", mil: " + System.currentTimeMillis() + " " +
                    "got a token");
        }
    }
}
