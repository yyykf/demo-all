package cn.ykf.factory;

import cn.ykf.factory.general.worst.service.Cache;
import cn.ykf.factory.general.worst.service.impl.CacheImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class AbstractFactoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFactoryTest.class);

    @Test
    void testWorst() {
        final Cache cache = new CacheImpl();

        cache.set("code4j", "鱼开饭", 1);
        String val = cache.get("code4j", 1);
        LOGGER.info("缓存集群升级，测试结果：{}", val);
    }

}
