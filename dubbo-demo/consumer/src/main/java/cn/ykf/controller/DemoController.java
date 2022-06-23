package cn.ykf.controller;

import cn.ykf.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YuKaiFan
 * @date 2022/6/23
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
