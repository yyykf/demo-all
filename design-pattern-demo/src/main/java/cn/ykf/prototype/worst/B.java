package cn.ykf.prototype.worst;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class B extends A implements Cloneable{

    public B(int a) {
        super(a);
    }

    public B() {
        System.out.println("B类的构造方法执行了");
    }

    @Override
    protected A clone() throws CloneNotSupportedException {
        // final A a = new A(super.a);
        // return a;

        final B clone = (B) super.clone();
        return clone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        final B b = new B(1);
        // final B clone = (B) b.clone();
        final A clone = b.clone();

        System.out.println(clone.getClass() == B.class);

    }
}
