package cn.ykf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author YuKaiFan
 * @date 2022/6/23
 */
@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class ConsumerWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerWebApplication.class);
    }
}
