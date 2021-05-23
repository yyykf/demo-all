import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 外部化配置测试类
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021-05-23
 */
class ExternalConfigTest {

    @Test
    void test() {
        // 创建配置上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboConfiguration.class);

        // application
        ApplicationConfig applicationConfig = context.getBean("applicationBean", ApplicationConfig.class);
        System.out.printf("applicationBean.name = %s \n", applicationConfig.getName());

        // module
        ModuleConfig moduleConfig = context.getBean("moduleBean", ModuleConfig.class);
        System.out.printf("moduleBean.name = %s \n", moduleConfig.getName());

        // protocol
        ProtocolConfig protocolConfig = context.getBean(ProtocolConfig.class);
        System.out.printf("protocolConfig.name = %s \n", protocolConfig.getName());
        System.out.printf("protocolConfig.port = %s \n", protocolConfig.getPort());

        // provider
        ProviderConfig providerConfig = context.getBean(ProviderConfig.class);
        System.out.printf("providerConfig.name = %s \n", providerConfig.getHost());

        // consumer
        ConsumerConfig consumerConfig = context.getBean(ConsumerConfig.class);
        System.out.printf("consumerConfig.name = %s \n", consumerConfig.getClient());
    }

    @EnableDubboConfig
    @PropertySource("classpath:config.properties")
    @Configuration
    public static class DubboConfiguration {

    }
}
