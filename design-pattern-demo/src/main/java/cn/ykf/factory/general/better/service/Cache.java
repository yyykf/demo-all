package cn.ykf.factory.general.better.service;

import java.util.concurrent.TimeUnit;

/**
 * @author YuKaiFan
 * @date 2022/3/24
 */
public interface Cache {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);
}
