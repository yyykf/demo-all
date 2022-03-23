package cn.ykf.factory.general.worst.service;

import java.util.concurrent.TimeUnit;

/**
 * @author YuKaiFan
 * @date 2022/3/24
 */
public interface Cache {

    String get(String key, int redisType);

    void set(String key, String value, int redisType);

    void set(String key, String value, long timeout, TimeUnit timeUnit, int redisType);

    void del(String key, int redisType);
}
