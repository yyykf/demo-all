package cn.ykf.spi.service.impl;

import cn.ykf.spi.service.LogService;

/**
 * log4j 日志实现
 *
 * @author YuKaiFan
 * @date 2021/5/12
 */
public class Log4j implements LogService {

    static {
        System.out.println("log4j 实现类被初始化了...");
    }

    @Override
    public void log(String info) {
        System.out.printf("%s: %s%n", "log4j", info);
    }
}
