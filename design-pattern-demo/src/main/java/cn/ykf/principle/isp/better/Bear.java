package cn.ykf.principle.isp.better;

import cn.ykf.principle.isp.worst.Animal;

/**
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class Bear implements Eat, Hibernation {

    @Override
    public void eat() {
        System.out.println("熊会进食");
    }

    @Override
    public void hibernation() {
        System.out.println("熊会冬眠");
    }
}
