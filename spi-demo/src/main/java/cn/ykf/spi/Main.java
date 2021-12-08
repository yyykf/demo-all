package cn.ykf.spi;

import cn.ykf.spi.service.LogService;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.sql.*;
import java.util.*;

/**
 * 主类
 *
 * @author YuKaiFan
 * @date 2021/5/12
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        jdkSpi();
        System.out.println("-----------");
        dubboSpi();
    }

    /**
     * jdk spi 实现
     */
    private static void jdkSpi() {
        ServiceLoader<LogService> logs = ServiceLoader.load(LogService.class);
        Iterator<LogService> iterator = logs.iterator();

        // 遍历调用实现类
        iterator.forEachRemaining(log -> log.log("hello world~"));
    }

    /**
     * dubbo spi 实现
     */
    private static void dubboSpi() {
        // 加载实现类
        ExtensionLoader<LogService> loader = ExtensionLoader.getExtensionLoader(LogService.class);

        // 获取默认扩展实现类
        LogService defaultExtension = loader.getDefaultExtension();
        if (Objects.nonNull(defaultExtension)) {
            defaultExtension.log("hello world~(echo by default extension)");
        }

        // 根据配置文件中的扩展名按需获取实现类
        LogService logback = loader.getExtension("logback");
        if (Objects.nonNull(logback)) {
            logback.log("hello world~");
        }

        // 加载的扩展实现类集合
        System.out.println(loader.getLoadedExtensions());
    }
}
