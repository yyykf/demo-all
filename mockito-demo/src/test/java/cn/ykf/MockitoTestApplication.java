package cn.ykf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * test application
 *
 * @author YuKaiFan
 * @date 2021/5/7
 */
@EnableAutoConfiguration
@ComponentScan({"cn.ykf.service.impl"})
public class MockitoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockitoTestApplication.class, args);
    }
}
