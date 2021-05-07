package cn.ykf.service;

/**
 * Demo Service
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/7
 */
public interface DemoService {

    /**
     * say hello
     *
     * @param name name
     * @return {@code hello,${name}}
     */
    String hello(String name);
}
