package cn.ykf;

import cn.ykf.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务消费者
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/13
 */
public class ConsumerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        // 远程调用
        DemoService service = context.getBean(DemoService.class);
        System.out.println(service.sayHello("ykf"));

        System.out.println("服务调用完成...");
    }
}
