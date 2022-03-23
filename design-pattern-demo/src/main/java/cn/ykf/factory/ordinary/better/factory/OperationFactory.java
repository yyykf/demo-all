package cn.ykf.factory.ordinary.better.factory;

import cn.ykf.factory.ordinary.better.service.Operation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author YuKaiFan
 * @date 2022/3/21
 */
@Component
public class OperationFactory {

    public static final Map<String, Operation> OPERATIONS = new HashMap<>(8);

    @Resource
    private List<Operation> operations;

    @PostConstruct
    public void initOperations() {
        OPERATIONS.putAll(operations.stream().collect(Collectors.toMap(Operation::getKey, Function.identity())));
    }

    public Operation getOperation(String operator) {
        return OPERATIONS.get(operator);
    }
}
