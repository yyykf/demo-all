/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ykf.jdk.queue;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Can completely solve the OOM problem caused by {@link java.util.concurrent.LinkedBlockingQueue},
 * does not depend on {@link java.lang.instrument.Instrumentation} and is easier to use than
 * {@link MemoryLimitedLinkedBlockingQueue}.
 *
 * @see
 * <a href="https://github.com/apache/incubator-shenyu/blob/master/shenyu-common/src/main/java/org/apache/shenyu/common/concurrent/MemorySafeLinkedBlockingQueue.java">MemorySafeLinkedBlockingQueue</a>
 * @see <a href="https://github.com/apache/dubbo/pull/10021">[ISSUE #10020] add MemorySafeLinkedBlockingQueue #10021</a>
 */
public class MemorySafeLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {

    public static int THE_256_MB = 256 * 1024 * 1024;

    /**
     * the maximum amount of available memory that needs to reserved for the jvm.
     *
     * eg, set maxFreeMemory to 256M represents that we should reserve 256MB to jvm.
     * when the jvm's maximum available amount of memory less than it, the put operation will fail.
     * therefore, the element of queue will not increase without limited.
     */
    private int maxFreeMemory;

    public MemorySafeLinkedBlockingQueue() {
        // default 256MB
        this(THE_256_MB);
    }

    public MemorySafeLinkedBlockingQueue(final int maxFreeMemory) {
        super(Integer.MAX_VALUE);
        this.maxFreeMemory = maxFreeMemory;
    }

    public MemorySafeLinkedBlockingQueue(final Collection<? extends E> c,
                                         final int maxFreeMemory) {
        super(c);
        this.maxFreeMemory = maxFreeMemory;
    }

    /**
     * set the max free memory.
     *
     * @param maxFreeMemory the max free memory
     */
    public void setMaxFreeMemory(final int maxFreeMemory) {
        this.maxFreeMemory = maxFreeMemory;
    }

    /**
     * get the max free memory.
     *
     * @return the max free memory limit
     */
    public int getMaxFreeMemory() {
        return maxFreeMemory;
    }

    /**
     * determine if there is any remaining free memory.
     *
     * @return true if has free memory
     */
    public boolean hasRemainedMemory() {
        return MemoryLimitCalculator.maxAvailable() > maxFreeMemory;
    }

    @Override
    public void put(final E e) throws InterruptedException {
        if (hasRemainedMemory()) {
            super.put(e);
        }
    }

    @Override
    public boolean offer(final E e, final long timeout, final TimeUnit unit) throws InterruptedException {
        return hasRemainedMemory() && super.offer(e, timeout, unit);
    }

    @Override
    public boolean offer(final E e) {
        return hasRemainedMemory() && super.offer(e);
    }
}
