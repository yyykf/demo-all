package cn.ykf.subject;

/**
 * verify subject
 *
 * @author YuKaiFan
 * @date 2021/5/7
 */
public class VerifySubject {

    public void methodA() {
        System.out.println("method a...");
    }

    public void methodB(int i) {
        int key = i % 2;

        switch (key) {
            case 0:
                methodA();
                break;
            case 1:
                methodC();
                break;
        }
    }

    public void methodC() {
        System.out.println("method c...");
    }

}
