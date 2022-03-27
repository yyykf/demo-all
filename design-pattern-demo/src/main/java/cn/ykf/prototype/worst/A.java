package cn.ykf.prototype.worst;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class A implements Cloneable{

    protected int a ;

    public A() {
        System.out.println("A类的构造方法执行了");
    }

    public A(int a) {
        this.a = a;
    }

    @Override
    protected A clone() throws CloneNotSupportedException {
        final A clone = (A) super.clone();
        return clone;
    }
}
