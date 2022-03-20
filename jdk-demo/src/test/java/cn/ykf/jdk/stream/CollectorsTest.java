package cn.ykf.jdk.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 收集器相关测试类
 *
 * @author YuKaiFan
 * @date 2022/3/20
 */
public class CollectorsTest {

    @Test
    void testPartitioningBy() {
        // 直接根据奇偶进行分区
        final Map<Boolean, List<Integer>> evenMap = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        // 获取奇数
        System.out.printf("「偶数」%n%s%n", evenMap.get(true));
        // 获取偶数
        System.out.printf("「奇数」%n%s%n", evenMap.get(false));
    }

    @Test
    void testGroupingBy() {
        final Map<Integer, Optional<Integer>> max = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.groupingBy(i -> i % 2, Collectors.maxBy(Integer::compare)));
        // 奇数最大值
        System.out.println(max.getOrDefault(0, Optional.of(Integer.MAX_VALUE)));
        // 偶数最大值
        System.out.println(max.getOrDefault(1, Optional.of(Integer.MAX_VALUE)));

        // 按照奇偶分区，放入 LinkedHashMap 集合中
        final LinkedHashMap<Integer, Set<String>> collect = IntStream.rangeClosed(1, 100).boxed()
                .collect(Collectors.groupingBy(i -> i % 2, LinkedHashMap::new,
                        // 每个分区的元素，映射为String，并重新收集为 Set
                        Collectors.mapping(String::valueOf, Collectors.toSet())));
        System.out.println(collect);

        // 统计
        final Map<Integer, IntSummaryStatistics> count = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.groupingBy(i -> i % 2, Collectors.summarizingInt(Integer::intValue)));
        System.out.println(count.get(0));
        System.out.println(count.get(1));
    }
}
