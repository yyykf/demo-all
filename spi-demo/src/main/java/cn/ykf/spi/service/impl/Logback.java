package cn.ykf.spi.service.impl;

import cn.ykf.spi.service.LogService;

/**
 * logback 日志实现
 *
 * @author YuKaiFan
 * @date 2021/5/12
 */
public class Logback implements LogService {

    static {
        System.out.println("logback 实现类被初始化了...");
    }

    @Override
    public void log(String info) {
        System.out.printf("%s: %s%n", "logback", info);
    }
}
