package cn.ykf.factory.ordinary.worst.service.impl;

import cn.ykf.factory.ordinary.worst.service.Operation;

/**
 * @author YuKaiFan
 * @date 2022/3/23
 */
public class SubtractionOperation implements Operation {

    @Override
    public Number compute(Number a, Number b) {
        return a.intValue() - b.intValue();
    }
}
