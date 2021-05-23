import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfigBinding;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfigBindings;
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
    void testSingleBindBean() {
        // 创建配置上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboConfiguration.class);

        System.out.println(context.getBean(DubboConfiguration.class));

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

    @Test
    void testMultipleBindBean() {
        // 创建配置上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboMultipleConfiguration.class);

        // 获取 ApplicationConfig Bean："applicationBean"、"applicationBean2" 和 "applicationBean3"
        ApplicationConfig applicationBean = context.getBean("applicationBean", ApplicationConfig.class);
        ApplicationConfig applicationBean2 = context.getBean("applicationBean2", ApplicationConfig.class);
        ApplicationConfig applicationBean3 = context.getBean("applicationBean3", ApplicationConfig.class);

        System.out.printf("applicationBean.name = %s \n", applicationBean.getName());
        System.out.printf("applicationBean2.name = %s \n", applicationBean2.getName());
        System.out.printf("applicationBean3.name = %s \n", applicationBean3.getName());
    }

    @Test
    void testBinding() {
        // 创建配置上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboBindingConfiguration.class);

        // 获取 ApplicationConfig Bean："applicationBean"、"applicationBean2" 和 "applicationBean3"
        ApplicationConfig applicationBean = context.getBean("applicationBean", ApplicationConfig.class);
        ApplicationConfig applicationBean2 = context.getBean("applicationBean2", ApplicationConfig.class);
        ApplicationConfig applicationBean3 = context.getBean("applicationBean3", ApplicationConfig.class);

        System.out.printf("applicationBean.name = %s \n", applicationBean.getName());
        System.out.printf("applicationBean2.name = %s \n", applicationBean2.getName());
        System.out.printf("applicationBean3.name = %s \n", applicationBean3.getName());

        // 获取 ModuleConfig Bean："moduleBean"
        ModuleConfig moduleBean = context.getBean("moduleBean", ModuleConfig.class);

        System.out.printf("moduleBean.name = %s \n", moduleBean.getName());
    }

    /**
     * 单 dubbo bean 绑定配置类
     */
    @EnableDubboConfig(multiple = false)
    @PropertySource("classpath:config.properties")
    @Configuration
    public static class DubboConfiguration {

    }

    /**
     * 多 dubbo bean 配置类
     */
    @EnableDubboConfig
    @PropertySource("classpath:multiple-config.properties")
    @Configuration
    public static class DubboMultipleConfiguration {

    }

    /**
     * 定制化绑定 bean 配置类
     */
    @EnableDubboConfigBindings({
            @EnableDubboConfigBinding(prefix = "${applications.prefix}", type = ApplicationConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.module", type = ModuleConfig.class)
    })
    @PropertySource("classpath:bindings.properties")
    @Configuration
    public static class DubboBindingConfiguration {
    }


}
