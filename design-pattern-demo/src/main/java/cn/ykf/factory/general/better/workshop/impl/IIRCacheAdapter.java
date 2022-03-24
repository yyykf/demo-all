package cn.ykf.factory.general.better.workshop.impl;

import cn.ykf.factory.general.better.workshop.ICacheAdapter;
import cn.ykf.factory.general.common.cluster.IIR;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class IIRCacheAdapter implements ICacheAdapter {

    private final IIR iir = new IIR();

    @Override
    public String get(String key) {
        return iir.get(key);
    }

    @Override
    public void set(String key, String value) {
        iir.set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    @Override
    public void del(String key) {
        iir.del(key);
    }
}
