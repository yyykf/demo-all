package cn.ykf.jdk.timer;

import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时器测试类
 * <a href="https://www.baeldung.com/java-timer-and-timertask">Java Timer</a>
 *
 * @author YuKaiFan
 * @date 2021/6/2
 */
class TimerTest {

    @Test
    @DisplayName("测试jdk定时器基本用法")
    void testTimer() throws InterruptedException {
        // todo 了解一下和netty的时间轮算法区别
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delay 3s, task runs...");

                countDownLatch.countDown();
            }
        }, 3000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("Now is %s...%n", new Date().toString());

                countDownLatch.countDown();
            }
        }, Date.from(LocalDateTime.now().plusSeconds(5).atZone(ZoneId.systemDefault()).toInstant()));

        // avoid to close before task runs
        countDownLatch.await();
    }

    @Test
    @DisplayName("测试fixed delay定时任务")
    void testFixedDelay() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        // fixed delay 为上一个任务结束时间和下一个任务开始时间的间隔
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("Now is %s...%n",
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
            }
        }, 0, 1000);

        countDownLatch.await();
    }

    @Test
    @DisplayName("测试fixed rate定时任务")
    void testFixedRate() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        AtomicInteger counter = new AtomicInteger();

        System.out.println("Now is " + LocalDateTime.now());

        // fixed rate 为两次任务开始时间的间隔
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("%d: Now is %s...%n", counter.incrementAndGet(),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));

                countDownLatch.countDown();
            }
        }, 1000, 2000);

        countDownLatch.await();
    }
}
