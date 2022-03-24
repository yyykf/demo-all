package cn.ykf.factory.general.better.workshop.impl;

import cn.ykf.factory.general.better.workshop.ICacheAdapter;
import cn.ykf.factory.general.common.cluster.EGM;
import java.util.concurrent.TimeUnit;

/**
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class EGMCacheAdapter implements ICacheAdapter {

    private final EGM egm = new EGM();

    @Override
    public String get(String key) {
        return egm.gain(key);
    }

    @Override
    public void set(String key, String value) {
        egm.set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    @Override
    public void del(String key) {
        egm.delete(key);
    }
}
