package cn.ykf.builder.common.ceiling;

import cn.ykf.builder.common.Matter;
import java.math.BigDecimal;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class LevelTwoCeiling implements Matter {

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
        return "二级";
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(850L);
    }

    @Override
    public String desc() {
        return "两个层次的吊顶，二级吊顶高度一般就往下吊20cm，要是层高很高，也可增加每级的厚度";
    }
}
