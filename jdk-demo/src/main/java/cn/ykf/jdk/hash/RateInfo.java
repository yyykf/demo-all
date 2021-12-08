package cn.ykf.jdk.hash;

/**
 * 哈希统计信息
 *
 * @author YuKaiFan
 * @date 2021-12-08
 */
public class RateInfo {

    /** 乘数 */
    private int multiplier;
    /** 最小哈希值 */
    private int minHash;
    /** 最大哈希值 */
    private int maxHash;
    /** 冲突数 */
    private int collisionCount;
    /** 碰撞比例 */
    private double collisionRate;

    public RateInfo(int multiplier, int minHash, int maxHash, int collisionCount, double collisionRate) {
        this.multiplier = multiplier;
        this.minHash = minHash;
        this.maxHash = maxHash;
        this.collisionCount = collisionCount;
        this.collisionRate = collisionRate;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public RateInfo setMultiplier(int multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public int getMinHash() {
        return minHash;
    }

    public RateInfo setMinHash(int minHash) {
        this.minHash = minHash;
        return this;
    }

    public int getMaxHash() {
        return maxHash;
    }

    public RateInfo setMaxHash(int maxHash) {
        this.maxHash = maxHash;
        return this;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public RateInfo setCollisionCount(int collisionCount) {
        this.collisionCount = collisionCount;
        return this;
    }

    public double getCollisionRate() {
        return collisionRate;
    }

    public RateInfo setCollisionRate(double collisionRate) {
        this.collisionRate = collisionRate;
        return this;
    }
}
