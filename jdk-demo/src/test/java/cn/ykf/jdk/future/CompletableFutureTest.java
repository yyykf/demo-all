package cn.ykf.jdk.future;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 测试类
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/11
 */
public class CompletableFutureTest {

    @Test
    @DisplayName("简单用法")
    void test() {
        CompletableFuture<String> normalFuture = this.wrapFuture(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 0;
        }));

        CompletableFuture<String> exceptionFuture = this.wrapFuture(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            throw new RuntimeException("异步任务执行出错");
        }));

        System.out.println(Thread.currentThread().getName() + ": 等待异步任务执行完成.");

        normalFuture.join();
        exceptionFuture.join();
    }

    @Test
    @DisplayName("whenComplete")
    void testWhenComplete() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "任务执行完毕.";
        }).whenComplete((result, t) -> {
            // 想异步处理结果的话使用 whenCompleteAsync
            if (t != null) {
                System.out.println(Thread.currentThread().getName() + ": 异步任务出错了.");
                return;
            }

            System.out.println(Thread.currentThread().getName() + ": 异步任务结果 - " + result);
        });

        System.out.println(Thread.currentThread().getName() + ": 等待异步任务执行完成.");

        // @see java.util.concurrent.ForkJoinPool.commonPool
        waitForCommonPool();
    }

    @Test
    @DisplayName("分开处理")
    void testExceptionally() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            throw new RuntimeException("异步任务执行出错");
        }).thenAccept(result -> {
            // 正常处理结果
            System.out.println(Thread.currentThread().getName() + ": 异步任务结果 - " + result);
        }).exceptionally(t -> {
            // 异常处理结果
            System.out.println(Thread.currentThread().getName() + ": 异步任务出错了.");
            return null;
        });

        System.out.println(Thread.currentThread().getName() + ": 等待异步任务执行完成.");

        waitForCommonPool();
    }

    @Test
    @DisplayName("测试多个任务串行")
    void testThenApply() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务A.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "任务A执行完毕.";
        }).thenApply(result -> {
            System.out.println(Thread.currentThread().getName() + ": " + result + "开始执行异步任务B");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "所有任务执行完毕了.";
        }).thenAcceptAsync(result -> System.out.println(Thread.currentThread().getName() + ": " + result));

        waitForCommonPool();
    }

    @Test
    @DisplayName("测试多个任务并行")
    void testAnyOf() {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务A.");
            try {
                int time = ThreadLocalRandom.current().nextInt(5);
                TimeUnit.SECONDS.sleep(time);
                System.out.println(Thread.currentThread().getName() + ": 任务A耗时为" + time + "s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "任务A执行完毕.";
        });

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务B.");
            try {
                int time = ThreadLocalRandom.current().nextInt(5);
                TimeUnit.SECONDS.sleep(time);
                System.out.println(Thread.currentThread().getName() + ": 任务B耗时为" + time + "s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "任务B执行完毕.";
        });

        CompletableFuture.anyOf(futureA, futureB).thenAccept(System.out::println);

        waitForCommonPool();
    }

    @Test
    @DisplayName("DEBUG用")
    void testForDebug() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("执行异步任务");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务执行完毕");
        });

        Assertions.assertNull(future.get());
    }

    /**
     * 等待 Completable 默认线程池中的任务执行完毕
     *
     * @see java.util.concurrent.ForkJoinPool#commonPool()
     */
    private void waitForCommonPool() {
        while (!ForkJoinPool.commonPool().awaitQuiescence(1, TimeUnit.SECONDS)) {
            System.out.println(Thread.currentThread().getName() + ": 异步任务还没有执行完毕，继续等待...");
        }
    }

    /**
     * 包装一下CompletableFuture，添加后续处理
     *
     * @param future 待包装的future
     * @return 包装后的future
     */
    private CompletableFuture<String> wrapFuture(CompletableFuture<?> future) {
        return future.handleAsync((result, t) -> {
            // Mapping 返回值
            if (t != null) {
                System.out.println(Thread.currentThread().getName() + ": 异步任务出错了.");
                return "error.";
            }

            return "success.";
        }).thenApplyAsync(result -> {
            // 处理返回结果
            System.out.println(Thread.currentThread().getName() + ": 异步任务结果 - " + result);

            return result;
        });
    }
}
