package cn.ykf.spi;

import cn.ykf.spi.service.LogService;

import java.sql.*;
import java.util.*;

/**
 * 主类
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/12
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        jdkSpi();
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

}
