package cn.ykf.jdk.hash;

/**
 * 扰动函数
 *
 * @author YuKaiFan
 * @date 2021/12/8
 */
public class Disturb {

    /**
     * 混合高16位和低16位计算桶下标
     *
     * @param key  key
     * @param size 桶长度
     * @return 经过扰动函数处理的桶下标
     */
    public static int disturbHashIdx(String key, int size) {
        int h;
        return (size - 1) & ((h = key.hashCode()) ^ (h >>> 16));
    }

    /**
     * 非扰动函数计算桶下标
     *
     * @param key  key
     * @param size 桶长度
     * @return 未经过扰动函数处理的桶下标
     */
    public static int hashIdx(String key, int size) {
        return (size - 1) & key.hashCode();
    }
}
