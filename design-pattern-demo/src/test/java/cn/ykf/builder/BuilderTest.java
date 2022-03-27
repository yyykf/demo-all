package cn.ykf.builder;

import cn.ykf.builder.better.Builder;
import cn.ykf.builder.worst.DecorationPackageController;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class BuilderTest {


    @Test
    void bad() {
        final DecorationPackageController decoration = new DecorationPackageController();

        // 豪华欧式
        System.out.println(decoration.getMatterList(new BigDecimal("132.52"),1));

        // 轻奢田园
        System.out.println(decoration.getMatterList(new BigDecimal("98.25"),2));

        // 现代简约
        System.out.println(decoration.getMatterList(new BigDecimal("85.43"),3));
    }

    @Test
    void better(){
        Builder builder = new Builder();

        // 豪华欧式
        System.out.println(builder.levelOne(132.52D).getDetail());

        // 轻奢田园
        System.out.println(builder.levelTwo(98.25D).getDetail());

        // 现代简约
        System.out.println(builder.levelThree(85.43D).getDetail());
    }
}
