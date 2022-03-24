package cn.ykf.factory.general.common.cluster;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟IIR集群
 *
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class IIR {

    private Logger LOGGER = LoggerFactory.getLogger(IIR.class);

    private final Map<String, String> dataMap = new ConcurrentHashMap<String, String>();

    public String get(String key) {
        LOGGER.info("IIR获取数据 key：{}", key);
        return dataMap.get(key);
    }

    public void set(String key, String value) {
        LOGGER.info("IIR写入数据 key：{} val：{}", key, value);
        dataMap.put(key, value);
    }

    public void setExpire(String key, String value, long timeout, TimeUnit timeUnit) {
        LOGGER.info("IIR写入数据 key：{} val：{} timeout：{} timeUnit：{}", key, value, timeout, timeUnit.toString());
        dataMap.put(key, value);
    }

    public void del(String key) {
        LOGGER.info("IIR删除数据 key：{}", key);
        dataMap.remove(key);
    }
}
