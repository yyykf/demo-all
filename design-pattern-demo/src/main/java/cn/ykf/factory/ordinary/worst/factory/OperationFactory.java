package cn.ykf.factory.ordinary.worst.factory;


import cn.ykf.factory.ordinary.worst.service.Operation;
import cn.ykf.factory.ordinary.worst.service.impl.AddOperation;
import cn.ykf.factory.ordinary.worst.service.impl.SubtractionOperation;

/**
 * @author YuKaiFan
 * @date 2022/3/23
 */
public class OperationFactory {

    public Operation getOperation(String operator) {
        switch (operator) {
            case "+":
                return new AddOperation();
            case "-":
                return new SubtractionOperation();
            default:
                return null;
        }
    }
}
