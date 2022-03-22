package cn.ykf.principle.isp.worst;

/**
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class Bear implements Animal {

    @Override
    public void eat() {
        System.out.println("熊会进食");
    }

    @Override
    public void hibernation() {
        System.out.println("熊会冬眠");
    }

    @Override
    public void fly() {
        // 熊不会飞
    }
}
