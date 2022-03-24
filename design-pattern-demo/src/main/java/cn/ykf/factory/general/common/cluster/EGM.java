package cn.ykf.factory.general.common.cluster;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟EGM集群
 *
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class EGM {

    private Logger LOGGER = LoggerFactory.getLogger(EGM.class);

    private final Map<String, String> dataMap = new ConcurrentHashMap<String, String>();

    public String gain(String key) {
        LOGGER.info("EGM获取数据 key：{}", key);
        return dataMap.get(key);
    }

    public void set(String key, String value) {
        LOGGER.info("EGM写入数据 key：{} val：{}", key, value);
        dataMap.put(key, value);
    }

    public void setEx(String key, String value, long timeout, TimeUnit timeUnit) {
        LOGGER.info("EGM写入数据 key：{} val：{} timeout：{} timeUnit：{}", key, value, timeout, timeUnit.toString());
        dataMap.put(key, value);
    }

    public void delete(String key) {
        LOGGER.info("EGM删除数据 key：{}", key);
        dataMap.remove(key);
    }
}
