package cn.ykf.principle.isp.better;

/**
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class Bird implements Eat, Fly {

    @Override
    public void eat() {
        System.out.println("鸟会进食");
    }

    @Override
    public void fly() {
        System.out.println("鸟会飞翔");
    }
}
