package cn.ykf.principle.ocp.impl;

import cn.ykf.principle.ocp.IArea;

/**
 * 默认实现
 *
 * @author YuKaiFan
 * @date 2022/3/21
 */
public class DefaultIArea implements IArea {

    private static final double PI = 3.14;

    @Override
    public double computeCircle(double r) {
        return Math.pow(r, 2) * PI;
    }
}
