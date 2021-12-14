package cn.ykf.jdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 数据类型测试类
 *
 * @author YuKaiFan
 * @date 2021-12-14
 */
public class DataTypeTest {

    @Test
    void testChar() {
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

    @Test
    void testCompareUnsigned() {
        // 有符号数，0x80000000 即 -2147483648
        int a = Integer.MIN_VALUE;
        // 有符号数，0x7fffffff 即 2147483647
        int b = Integer.MAX_VALUE;
        assertTrue(a < b);

        // 如果是无符号数的话，那么 0x80000000 将大于 0x7fffffff
        assertEquals(1, Integer.compareUnsigned(a, b));
    }
}
