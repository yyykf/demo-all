package cn.ykf.jdk.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Future 测试类
 *
 * @author YuKaiFan
 * @date 2021/10/11
 */
public class FutureTest {

    @Test
    @DisplayName("Future")
    void testFuture() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        final AtomicInteger param = new AtomicInteger();

        Future<AtomicInteger> future = executor.submit(() -> {
            param.set(100);
        }, param);

        System.out.println("Future 的返回值为: " + future.get());

        executor.shutdown();
    }

    @Test
    @DisplayName("Guava.ListenableFuture")
    void testListenableFuture() throws InterruptedException {
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        ListenableFuture<String> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");

            TimeUnit.SECONDS.sleep(3);

            return "异步任务执行完毕.";
        });

        // 添加回调监听
        future.addListener(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ": Future 的返回值为 - " + future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);

        System.out.println(Thread.currentThread().getName() + ": 等待异步任务执行完毕时可以处理其他事件.");

        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }

    @Test
    @DisplayName("Guava.FutureCallback")
    void testFutureCallback() throws InterruptedException {
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        ListenableFuture<String> normalFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");

            TimeUnit.SECONDS.sleep(3);

            return "异步任务执行完毕.";
        });
        ListenableFuture<String> exceptionFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": 开始执行异步任务.");

            TimeUnit.SECONDS.sleep(3);

            throw new RuntimeException("异步任务执行出错了");
        });

        FutureCallback<String> callback = new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println(Thread.currentThread().getName() + ": Future 的返回值为 - " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(Thread.currentThread().getName() + ": Future 执行时抛出异常了.");
                t.printStackTrace();
            }
        };
        // 添加回调
        Futures.addCallback(normalFuture, callback, executor);
        Futures.addCallback(exceptionFuture, callback, executor);

        System.out.println(Thread.currentThread().getName() + ": 等待异步任务执行完毕时可以处理其他事件.");

        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
