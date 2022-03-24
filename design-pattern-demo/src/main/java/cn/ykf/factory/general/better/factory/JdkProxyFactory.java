package cn.ykf.factory.general.better.factory;

import cn.ykf.factory.general.better.service.Cache;
import cn.ykf.factory.general.better.workshop.ICacheAdapter;
import java.lang.reflect.Proxy;

/**
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class JdkProxyFactory {

    /**
     * 获取缓存服务代理对象
     *
     * @param cacheClazz   要代理的缓存接口
     * @param cacheAdapter 要使用的适配器类
     * @param <T>          缓存接口的类型
     * @return 缓存接口的代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T extends Cache> T getProxy(Class<T> cacheClazz, Class<? extends ICacheAdapter> cacheAdapter)
            throws InstantiationException, IllegalAccessException {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 对缓存接口的调用最终会委托给适配器类，最终通过集群服务去调用
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{cacheClazz},
                new JdkInvocationHandler(cacheAdapter.newInstance()));
    }
}
