package cn.ykf.jdk.lock;

import java.util.concurrent.TimeUnit;

/**
 * 使用包装类型作为锁的问题
 *
 * @author YuKaiFan
 * @date 2022/2/14
 */
public class ErrorSync {

    public static void main(String[] args) {
        final Thread a = new Thread(new TicketConsumer(10), "A");
        final Thread b = new Thread(new TicketConsumer(10), "B");
        a.start();
        b.start();
    }

    private static class TicketConsumer implements Runnable {

        private static volatile Integer ticket;

        public TicketConsumer(int ticket) {
            TicketConsumer.ticket = ticket;
        }

        @Override
        public void run() {
            while (true) {
                System.out.printf("%s开始抢第%d张票，待加锁对象hashCode为%d%n", Thread.currentThread().getName(), ticket,
                        System.identityHashCode(ticket));
                // 开始对第n张票加锁，期望只有一个线程能进入同步块
                synchronized (ticket) {
                    if (ticket <= 0) {
                        return;
                    }
                    System.out.printf("%s抢到了第%d张票，当前锁对象hashCode为%d%n", Thread.currentThread().getName(), ticket,
                            System.identityHashCode(ticket));
                    try {
                        // 模拟抢票耗时
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%s抢到第%d张票后对票数减一%n", Thread.currentThread().getName(), ticket--);
                }
            }
        }
    }
}
