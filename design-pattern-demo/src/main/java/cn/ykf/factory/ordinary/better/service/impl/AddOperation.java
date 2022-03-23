package cn.ykf.factory.ordinary.better.service.impl;

import cn.ykf.factory.ordinary.better.service.Operation;
import org.springframework.stereotype.Component;

/**
 * @author YuKaiFan
 * @date 2022/3/21
 */
@Component
public class AddOperation implements Operation {

    @Override
    public String getKey() {
        return "+";
    }

    @Override
    public Number compute(Number a, Number b) {
        return a.intValue() + b.intValue();
    }
}
