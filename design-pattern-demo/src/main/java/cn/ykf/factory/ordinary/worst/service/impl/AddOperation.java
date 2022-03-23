package cn.ykf.factory.ordinary.worst.service.impl;

import cn.ykf.factory.ordinary.worst.service.Operation;

/**
 * @author YuKaiFan
 * @date 2022/3/21
 */
public class AddOperation implements Operation {

    @Override
    public Number compute(Number a, Number b) {
        return a.intValue() + b.intValue();
    }
}
