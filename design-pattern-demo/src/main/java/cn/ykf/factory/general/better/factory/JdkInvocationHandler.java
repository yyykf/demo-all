package cn.ykf.factory.general.better.factory;

import cn.ykf.factory.general.better.workshop.ICacheAdapter;
import cn.ykf.factory.general.common.utils.ClassLoaderUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理拦截器
 *
 * @author YuKaiFan
 * @date 2022/3/24
 */
public class JdkInvocationHandler implements InvocationHandler {

    private final ICacheAdapter cacheAdapter;

    public JdkInvocationHandler(ICacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据要调用的 CacheService 的方法名，找到适配器中对应的方法并调用
        return ICacheAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter,args);
    }
}
