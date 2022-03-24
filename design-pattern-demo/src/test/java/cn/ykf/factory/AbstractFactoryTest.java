package cn.ykf.factory;

import cn.ykf.factory.general.better.factory.JdkProxyFactory;
import cn.ykf.factory.general.better.workshop.impl.EGMCacheAdapter;
import cn.ykf.factory.general.better.workshop.impl.IIRCacheAdapter;
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

    @Test
    void testBetter() throws InstantiationException, IllegalAccessException {
        // 获取 EGM 缓存服务
        final cn.ykf.factory.general.better.service.Cache egmProxy = JdkProxyFactory.getProxy(
                cn.ykf.factory.general.better.service.Cache.class, EGMCacheAdapter.class);
        egmProxy.set("code4j", "鱼开饭");
        String val = egmProxy.get("code4j");
        LOGGER.info("缓存服务 EGM 测试，egmProxy.get 测试结果：{}", val);

        // 获取 IIR 缓存服务
        final cn.ykf.factory.general.better.service.Cache iirProxy = JdkProxyFactory.getProxy(
                cn.ykf.factory.general.better.service.Cache.class, IIRCacheAdapter.class);
        iirProxy.set("code4j", "鱼开饭");
        val = iirProxy.get("code4j");
        LOGGER.info("缓存服务 IIR 测试，iirProxy.get 测试结果：{}", val);
    }
}
