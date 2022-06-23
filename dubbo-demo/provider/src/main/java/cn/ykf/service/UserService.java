package cn.ykf.service;

import cn.ykf.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试 rest 协议
 *
 * @author YuKaiFan
 * @date 2022/6/21
 */
public interface UserService {

    User get(HttpServletRequest request, String id);

    User get(HttpServletRequest request, String name, Integer age);

    Boolean registerUser(User user);
}
