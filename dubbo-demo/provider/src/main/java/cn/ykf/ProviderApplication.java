package cn.ykf;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * 服务提供者
 *
 * @author YuKaiFan
 * @date 2021/5/13
 */
public class ProviderApplication {

    public static void main(String[] args) {
        // 启动后到 zk 查看 /dubbo/cn.ykf.service.DemoService/providers 的节点信息，对比开启注册信息简化前后 URL 区别
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        System.out.println("服务已注册...");

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
