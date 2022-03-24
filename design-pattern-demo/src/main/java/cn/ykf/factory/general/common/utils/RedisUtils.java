package cn.ykf.factory.general.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟最初的单机服务
 *
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class RedisUtils {

    private Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    private final Map<String, String> dataMap = new ConcurrentHashMap<>();

    public String get(String key) {
        LOGGER.info("Redis获取数据 key：{}", key);
        return dataMap.get(key);
    }

    public void set(String key, String value) {
        LOGGER.info("Redis写入数据 key：{} val：{}", key, value);
        dataMap.put(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        LOGGER.info("Redis写入数据 key：{} val：{} timeout：{} timeUnit：{}", key, value, timeout, timeUnit.toString());
        dataMap.put(key, value);
    }

    public void del(String key) {
        LOGGER.info("Redis删除数据 key：{}", key);
        dataMap.remove(key);
    }
}
