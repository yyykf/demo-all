package cn.ykf.factory;

import cn.ykf.factory.ordinary.better.config.Config;
import cn.ykf.factory.ordinary.better.factory.OperationFactory;
import cn.ykf.factory.ordinary.better.service.Operation;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author YuKaiFan
 * @date 2022/3/23
 */
@SpringJUnitConfig(classes = Config.class)
public class OrdinaryFactoryTest {

    @Resource
    private OperationFactory operationFactory;

    @Test
    void test() {
        final Operation addOperation = operationFactory.getOperation("+");
        final Operation subtractionOperation = operationFactory.getOperation("-");
        Assertions.assertNotNull(addOperation);
        Assertions.assertNotNull(subtractionOperation);
    }

}
