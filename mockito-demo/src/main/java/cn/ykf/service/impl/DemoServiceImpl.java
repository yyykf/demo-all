package cn.ykf.service.impl;

import cn.ykf.service.DemoService;

/**
 * Demo Service Implemention
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/7
 */

public class DemoServiceImpl implements DemoService {

    public String hello(String name) {
        return "Hello," + name;
    }
}
