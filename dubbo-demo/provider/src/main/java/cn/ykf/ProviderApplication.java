package cn.ykf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * 服务提供者
 *
 * @author YuKaiFan
 * @date 2021/5/13
 */
public class ProviderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderApplication.class);

    public static void main(String[] args) {
        // 启动后到 zk 查看 /dubbo/cn.ykf.cn.ykf.service.DemoService/providers 的节点信息，对比开启注册信息简化前后 URL 区别
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        LOGGER.info("服务已注册...");

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
