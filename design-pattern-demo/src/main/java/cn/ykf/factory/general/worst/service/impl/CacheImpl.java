package cn.ykf.factory.general.worst.service.impl;

import cn.ykf.factory.general.common.cluster.EGM;
import cn.ykf.factory.general.common.cluster.IIR;
import cn.ykf.factory.general.worst.service.Cache;
import cn.ykf.factory.general.common.utils.RedisUtils;
import java.util.concurrent.TimeUnit;

/**
 * 模拟 redis 服务
 *
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class CacheImpl implements Cache {

    private RedisUtils redisUtils = new RedisUtils();
    private EGM egm = new EGM();
    private IIR iir = new IIR();

    @Override
    public String get(String key, int redisType) {
        // 最简单的方式，通过 type 选择
        if (redisType == 1) {
            return egm.gain(key);
        } else if (redisType == 2) {
            return iir.get(key);
        }

        return redisUtils.get(key);
    }

    @Override
    public void set(String key, String value, int redisType) {
        if (redisType == 1) {
            egm.set(key, value);
            return;
        } else if (redisType == 2) {
            iir.set(key, value);
            return;
        }

        redisUtils.set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit, int redisType) {
        if (redisType == 1) {
            egm.setEx(key, value, timeout, timeUnit);
            return;
        } else if (redisType == 2) {
            iir.setExpire(key, value, timeout, timeUnit);
            return;
        }

        redisUtils.set(key, value, timeout, timeUnit);
    }

    @Override
    public void del(String key, int redisType) {
        if (redisType == 1) {
            egm.delete(key);
            return;
        } else if (redisType == 2) {
            iir.del(key);
            return;
        }

        redisUtils.del(key);
    }
}
