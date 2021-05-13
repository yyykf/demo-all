package cn.ykf.service.impl;

import cn.ykf.service.DemoService;

/**
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/13
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
