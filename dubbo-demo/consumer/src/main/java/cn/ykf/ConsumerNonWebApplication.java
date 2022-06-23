package cn.ykf;

import cn.ykf.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务消费者
 *
 * @author YuKaiFan
 * @date 2021/5/13
 */
public class ConsumerNonWebApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        // 远程调用
        DemoService service = context.getBean(DemoService.class);
        System.out.println(service.sayHello("ykf"));

        System.out.println("服务调用完成...");
    }
}
