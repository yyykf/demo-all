package cn.ykf.builder.common.floor;

import cn.ykf.builder.common.Matter;
import java.math.BigDecimal;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class DefFloor implements Matter {

    @Override
    public String type() {
        return "地板";
    }

    @Override
    public String brand() {
        return "德尔(Der)";
    }

    @Override
    public String model() {
        return "A+";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(119);
    }

    @Override
    public String desc() {
        return "DER德尔集团是全球领先的专业木地板制造商，北京2008年奥运会家装和公装地板供应商";
    }

}
