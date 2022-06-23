package cn.ykf.controller;

import cn.ykf.common.Result;
import cn.ykf.model.User;
import cn.ykf.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author YuKaiFan
 * @date 2022/6/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public Result<User> get(HttpServletRequest request, @PathVariable("id") String id) {
        return Result.succ(userService.get(request, id));
    }

    @GetMapping("/getUser")
    public Result<User> get(HttpServletRequest request, String name, Integer age) {
        return Result.succ(userService.get(request, name, age));
    }

    @PostMapping("/registerUser")
    public Result<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
