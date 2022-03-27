package cn.ykf.builder.common.ceiling;

import cn.ykf.builder.common.Matter;
import java.math.BigDecimal;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class LevelOneCeiling implements Matter {

    @Override
    public String type() {
        return "吊顶";
    }

    @Override
    public String brand() {
        return "装修公司自带";
    }

    @Override
    public String model() {
        return "一级";
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(260L);
    }

    @Override
    public String desc() {
        return "造型只做低一级，只有一个层次的吊顶，一般离顶120-150mm";
    }
}
