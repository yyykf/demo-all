package cn.ykf.factory.general.better.workshop;

import java.util.concurrent.TimeUnit;

/**
 * 缓存适配器
 *
 * @author YuKaiFan
 * @date 2022/3/24
 */
public interface ICacheAdapter {

    String get(final String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);
}
