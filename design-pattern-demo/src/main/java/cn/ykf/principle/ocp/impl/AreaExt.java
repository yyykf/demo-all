package cn.ykf.principle.ocp.impl;

/**
 * 扩展实现
 *
 * @author YuKaiFan
 * @date 2022/3/21
 */
public class AreaExt extends DefaultIArea {

    private static final double PI = 3.141415926D;

    @Override
    public double computeCircle(double r) {
        return Math.pow(r, 2) * PI;
    }
}
