package cn.ykf.builder.common;

import java.math.BigDecimal;

/**
 * 装修材料接口
 *
 * @author YuKaiFan
 * @date 2022/3/27
 */
public interface Matter {

    /** 类型：地板、地砖、涂料、吊顶 */
    String type();

    /** 品牌 */
    String brand();

    /** 型号 */
    String model();

    /** 平米报价 */
    BigDecimal price();

    /** 描述 */
    String desc();
}
