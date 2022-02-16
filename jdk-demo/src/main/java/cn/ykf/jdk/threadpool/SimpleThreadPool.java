package cn.ykf.jdk.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单版的线程池
 *
 * @author YuKaiFan
 * @date 2022/2/15
 */
public class SimpleThreadPool implements Executor {

    /**
     * 记录当前工作线程数
     */
    private final AtomicInteger ctl = new AtomicInteger(0);
    /**
     * 核心线程数
     */
    private volatile int corePoolSize;
    /**
     * 最大线程数
     */
    private volatile int maximumPoolSize;
    /**
     * 任务队列
     */
    private final BlockingQueue<Runnable> workQueue;

    public SimpleThreadPool(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable command) {
        if (ctl.get() < corePoolSize) {
            // 当前线程数小于核心线程数的话，那就创建核心线程
            if (!addWorker(command)) {
                reject();
            }

            return;
        }

        // 核心线程数已满，那就尝试将任务入队
        if (!workQueue.offer(command)) {
            // 入队失败的话，尝试创建非核心线程
            if (!addWorker(command)) {
                reject();
            }
        }

    }

    private boolean addWorker(Runnable firstTask) {
        // 超过最大线程数
        if (ctl.get() >= maximumPoolSize) {
            return false;
        }

        final Worker worker = new Worker(firstTask);
        // 启动工作线程
        worker.thread.start();
        // 递增工作线程数
        ctl.incrementAndGet();

        return true;
    }

    private void reject() {
        throw new RejectedExecutionException(String.format("Error occurs. ctl: %d, workQueue size: %d", ctl.get(), workQueue.size()));
    }

    private final class Worker implements Runnable {

        Runnable firstTask;
        final Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            Runnable task = firstTask;

            try {
                while ((task != null) || (task = getTask()) != null) {
                    // 执行任务
                    task.run();
                    // 如果当前线程数超过最大线程数，那就让当前线程结束吧
                    if (ctl.get() > maximumPoolSize) {
                        break;
                    }
                    task = null;
                }
            } finally {
                // 结束时需要递减工作线程数
                ctl.decrementAndGet();
            }
        }
    }

    private Runnable getTask() {
        try {
            System.out.printf("workQueue size: %d%n", workQueue.size());
            return workQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        final SimpleThreadPool pool = new SimpleThreadPool(2, 2, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s:Task-%d executed!%n", Thread.currentThread().getName(), finalI);
            });
        }
    }
}
