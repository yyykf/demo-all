package cn.ykf.jdk.hash;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 哈希值工具类
 *
 * @author YuKaiFan
 * @date 2021-12-08
 */
public class HashCode {

    /**
     * 参照 String 的哈希值计算方法
     *
     * @param str        待计算哈希值的字符串
     * @param multiplier 乘数
     * @return 哈希值
     * @see String#hashCode()
     */
    public static int hashCode(String str, int multiplier) {
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }

        return hash;
    }

    /**
     * 计算每个乘数对应的哈希统计信息列表
     *
     * @param strSet      待计算哈希值的字符串集合
     * @param multipliers 多个乘数
     * @return 含有每个乘数对应的哈希统计信息列表
     */
    public static List<RateInfo> collisionRateList(Set<String> strSet, Integer... multipliers) {
        // 统计信息集合
        List<RateInfo> list = Lists.newArrayListWithCapacity(multipliers.length);

        for (Integer multiplier : multipliers) {
            // 对于每个乘数，计算出对应的哈希码以及对应的统计信息
            List<Integer> hashList = strSet.stream().map(str -> HashCode.hashCode(str, multiplier))
                    .collect(Collectors.toList());
            list.add(hashCollisionRate(hashList, multiplier));
        }

        return list;
    }

    /**
     * 统计指定乘数下哈希码的分布情况
     *
     * @param strSet     字符串集合
     * @param multiplier 乘数
     * @return 哈希码分布情况
     */
    public static Map<Integer, Integer> partitionStatisticsList(Set<String> strSet, Integer multiplier) {
        // 计算当前乘数下哈希码分布情况
        List<Integer> hashList = strSet.stream().map(str -> HashCode.hashCode(str, multiplier)).collect(Collectors.toList());
        // 统计分区情况
        return partitionStatistics(hashList);
    }

    /**
     * 将哈希码的取值范围分为64个区域，统计哈希码列表中的哈希码落在每个区域的数量，以此判断分布情况
     *
     * @param hashList 哈希码列表
     * @return 分布情况
     */
    private static Map<Integer, Integer> partitionStatistics(List<Integer> hashList) {
        // 分为64个分区，每个分区的大小
        int step = (int) (Math.pow(2, 32) / 64);
        Map<Integer, Integer> statistics = Maps.newHashMapWithExpectedSize(step);

        for (long i = Integer.MIN_VALUE, start = 0; i <= Integer.MAX_VALUE; i += step) {
            long min = i, max = min + step;

            // 统计每个分区的哈希码个数
            int count = (int) hashList.stream().filter(hash -> hash >= min && hash < max).count();
            statistics.put((int) start++, count);
        }

        return statistics;
    }

    /**
     * 计算对应乘数的哈希统计信息
     *
     * @param hashList   哈希值列表
     * @param multiplier 乘数
     * @return 哈希统计信息
     */
    private static RateInfo hashCollisionRate(List<Integer> hashList, Integer multiplier) {
        int size = hashList.size();

        // 最大哈希值
        Integer maxHash = hashList.stream().max(Integer::compareTo).orElse(hashList.get(0));
        // 最小哈希值
        Integer minHash = hashList.stream().min(Integer::compareTo).orElse(hashList.get(0));
        // 冲突数
        int collisionCount = (int) (size - hashList.stream().distinct().count());
        // 碰撞比例
        double collisionRate = (collisionCount * 1.0) / size;

        return new RateInfo(multiplier, minHash, maxHash, collisionCount, collisionRate);
    }
}
