package cn.ykf.jdk.hash;

import cn.hutool.core.util.RandomUtil;
import cn.ykf.jdk.utils.WordsUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 哈希相关测试类
 *
 * @author YuKaiFan
 * @date 2021/12/8
 */
public class HashTest {

    /**
     * 测试不同乘数下哈希码的统计信息
     */
    @Test
    void testHashCode() {
        // 乘数199虽然碰撞几率小，但是用int作为哈希码的话会有溢出
        List<RateInfo> rateInfos = HashCode.collisionRateList(WordsUtil.loadWordsFile(), 2, 3, 5, 7, 17, 31, 32, 33, 39,
                41, 199);
        rateInfos.forEach(rateInfo -> System.out.printf(
                "multiplier: %d, minHash: %d, maxHash: %d, collisionCount: %6d, collisionRate: %.4f%%%n ",
                rateInfo.getMultiplier(), rateInfo.getMinHash(), rateInfo.getMaxHash(),
                rateInfo.getCollisionCount(), rateInfo.getCollisionRate() * 100));

        System.out.println(Integer.MIN_VALUE + "," + Integer.MAX_VALUE);
    }

    /**
     * 测试不同乘数下哈希码的分布情况
     */
    @Test
    void testHashPartition() {
        System.out.println("Multiplier 2: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 2));
        System.out.println("Multiplier 3: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 3));
        System.out.println("Multiplier 5: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 5));
        System.out.println("Multiplier 7: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 7));
        System.out.println("Multiplier 17: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 17));
        System.out.println("Multiplier 31: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 31));
        System.out.println("Multiplier 32: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 32));
        System.out.println("Multiplier 33: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 33));
        System.out.println("Multiplier 39: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 39));
        System.out.println("Multiplier 41: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 41));
        System.out.println("Multiplier 199: " + HashCode.partitionStatisticsList(WordsUtil.loadWordsFile(), 199));
    }

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

    /**
     * 测试扰动函数
     */
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

    @Test
    void testResize() {
        List<String> list = Stream.generate(() -> RandomUtil.randomString(6)).limit(10).collect(Collectors.toList());

        for (String key : list) {
            // 原始hash值
            int h = key.hashCode();
            // 扰动函数
            int hash = h ^ (h >>> 16);
            // 扰动后的hash值的二进制形式
            String binaryHash = Integer.toBinaryString(hash);
            // 桶长度为 16 时的哈希值
            int idx16 = (16 - 1) & hash;
            // 桶长度为 32 时的哈希值
            int idx32 = (32 - 1) & hash;

            // 结论：如果原hash值（扰动后） & 扩容长度 == 0，那么该元素在新集合中的索引下标不变，如果不为0，那么新位置 = 原位置 + 扩容长度
            System.out.printf("Key: %s%n",key);
            System.out.printf("Index(16): %d, hash(binary): %s, (hash & old bucket size): %s%n", idx16, binaryHash,
                    Integer.toBinaryString(hash & 16));
            System.out.printf("Index(32): %d, hash(binary): %s, hashCode(binary): %s, idx32(binary): %s%n%n", idx32,
                    binaryHash, Integer.toBinaryString(h), Integer.toBinaryString(idx32));
        }
    }
}
