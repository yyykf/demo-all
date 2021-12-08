package cn.ykf.jdk.hash;

import cn.hutool.core.util.RandomUtil;
import cn.ykf.jdk.utils.WordsUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 哈希相关测试类
 *
 * @author YuKaiFan
 * @date 2021/12/8
 */
public class HashTest {

    /**
     * 最简单的散列表
     */
    @Test
    void testSimpleHashMap() {
        String[] tab = new String[8];
        final int length = tab.length;

        Stream.generate(() -> RandomUtil.randomString(6)).limit(10).forEach(key -> {
            // 模拟 HashMap 的散列
            int idx = key.hashCode() & length - 1;

            System.out.printf("key: %s, index: %d%n", key, idx);

            if (tab[idx] == null) {
                tab[idx] = key;
                return;
            }

            // 模拟链表
            tab[idx] = tab[idx] + "->" + key;
        });

        System.out.println(Arrays.toString(tab));
    }

    @Test
    void testDisturb() {
        // 假设桶长度为128
        final int bucketSize = 128;

        // key: disturb idx, value: count
        Map<Integer, AtomicInteger> disturbIdxMap = new ConcurrentHashMap<>();
        // key: without disturb idx, value: count
        Map<Integer, AtomicInteger> idxMap = new ConcurrentHashMap<>();

        WordsUtil.loadWordsFile().parallelStream().forEach(word -> {
            // 扰动函数计算idx
            int disturbHashIdx = Disturb.disturbHashIdx(word, bucketSize);
            // 非扰动函数计算idx
            int hashIdx = Disturb.hashIdx(word, bucketSize);

            // 统计扰动函数计算的下标个数
            AtomicInteger counterWithDisturb = disturbIdxMap.computeIfAbsent(disturbHashIdx, key -> new AtomicInteger());
            int disturbCount = counterWithDisturb.incrementAndGet();
            // 统计非扰动函数计算的下标个数
            AtomicInteger counterWithoutDisturb = idxMap.computeIfAbsent(hashIdx, key -> new AtomicInteger());
            int count = counterWithoutDisturb.incrementAndGet();

            System.out.printf("word: %s, idx by disturb: %d, disturbCount: %d, idx without disturb: %d, count: %d%n", word
                    , disturbHashIdx, disturbCount, hashIdx, count);
        });
    }
}
