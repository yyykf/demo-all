package cn.ykf.principle.isp.worst;

/**
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class Bird implements Animal {

    @Override
    public void eat() {
        System.out.println("鸟会进食");
    }

    @Override
    public void hibernation() {
        // 鸟不会冬眠
    }

    @Override
    public void fly() {
        System.out.println("鸟会飞翔");
    }
}
