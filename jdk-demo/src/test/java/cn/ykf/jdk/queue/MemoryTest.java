package cn.ykf.jdk.queue;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.jupiter.api.Test;

import java.lang.instrument.Instrumentation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author YuKaiFan
 * @date 2022/6/6
 */
public class MemoryTest {
    @Test
    public void test() throws Exception {
        ByteBuddyAgent.install();
        final Instrumentation instrumentation = ByteBuddyAgent.getInstrumentation();
        final long objectSize = instrumentation.getObjectSize((Runnable) () -> {
        });
        int maxFreeMemory = (int) MemoryLimitCalculator.maxAvailable();
        MemorySafeLinkedBlockingQueue<Runnable> queue = new MemorySafeLinkedBlockingQueue<>(maxFreeMemory);
        // all memory is reserved for JVM, so it will fail here
        assertThat(queue.offer(() -> {
        }), is(false));

        // maxFreeMemory-objectSize Byte memory is reserved for the JVM, so this will succeed
        queue.setMaxFreeMemory((int) (MemoryLimitCalculator.maxAvailable() - objectSize));
        assertThat(queue.offer(() -> {
        }), is(true));
    }
}
