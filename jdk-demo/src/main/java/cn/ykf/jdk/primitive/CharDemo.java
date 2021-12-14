package cn.ykf.jdk.primitive;

/**
 * char 基本类型
 *
 * @author YuKaiFan
 * @date 2021-12-14
 */
public class CharDemo {

    public static void main(String[] args) {
        // null字符，char默认值，打印出来相当与一个空格
        char nul = '\u0000';
        System.out.println(nul);
        // 对应整数0
        int zero = nul;
        System.out.println(zero);

        // 打印 Hello World
        String str = "Hello" + nul + "World";
        System.out.println(str);
        // 打印 HelloWorld
        str = "Hello" + "" + "World";
        System.out.println(str);
    }
}
