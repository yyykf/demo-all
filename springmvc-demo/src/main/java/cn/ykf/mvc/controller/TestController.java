package cn.ykf.mvc.controller;

import cn.ykf.mvc.common.Request;
import cn.ykf.mvc.enums.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author YuKaiFan mailto:yukf@pvc123.com
 * @date 2021/12/7
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Integer testForm(ResultCode code) {
        return code.getCode();
    }

    @PostMapping
    public ResultCode testJson(@RequestBody Request code) {
        return code.getCode();
    }
}
