package cn.ykf.builder.worst;

import cn.ykf.builder.common.Matter;
import cn.ykf.builder.common.ceiling.LevelOneCeiling;
import cn.ykf.builder.common.ceiling.LevelTwoCeiling;
import cn.ykf.builder.common.coat.DuluxCoat;
import cn.ykf.builder.common.coat.LiBangCoat;
import cn.ykf.builder.common.floor.ShengXiangFloor;
import cn.ykf.builder.common.tile.DongPengTile;
import cn.ykf.builder.common.tile.MarcoPoloTile;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class DecorationPackageController {

    /**
     * 获取装修清单
     *
     * @param area  平方数
     * @param style 装修风格
     * @return 装修清单
     */
    public String getMatterList(BigDecimal area, Integer style) {

        // 装修清单
        final List<Matter> list = new ArrayList<>();
        // 装修价格
        BigDecimal price = BigDecimal.ZERO;

        if (style == 1) {
            // 豪华欧式

            // 吊顶，二级顶
            LevelTwoCeiling levelTwoCeiling = new LevelTwoCeiling();
            // 涂料，多乐士
            DuluxCoat duluxCoat = new DuluxCoat();
            // 地板，圣象
            ShengXiangFloor shengXiangFloor = new ShengXiangFloor();

            list.add(levelTwoCeiling);
            list.add(duluxCoat);
            list.add(shengXiangFloor);

            price = price.add(area.multiply(new BigDecimal("0.2")).multiply(levelTwoCeiling.price()));
            price = price.add(area.multiply(new BigDecimal("1.4")).multiply(duluxCoat.price()));
            price = price.add(area.multiply(shengXiangFloor.price()));
        } else if (style == 2) {
            // 轻奢田园

            // 吊顶，二级顶
            LevelTwoCeiling levelTwoCeiling = new LevelTwoCeiling();
            // 涂料，立邦
            LiBangCoat liBangCoat = new LiBangCoat();
            // 地砖，马可波罗
            MarcoPoloTile marcoPoloTile = new MarcoPoloTile();

            list.add(levelTwoCeiling);
            list.add(liBangCoat);
            list.add(marcoPoloTile);

            price = price.add(area.multiply(new BigDecimal("0.2")).multiply(levelTwoCeiling.price()));
            price = price.add(area.multiply(new BigDecimal("1.4")).multiply(liBangCoat.price()));
            price = price.add(area.multiply(marcoPoloTile.price()));
        } else if (style == 3) {
            // 现代简约

            // 吊顶，二级顶
            LevelOneCeiling levelOneCeiling = new LevelOneCeiling();
            // 涂料，立邦
            LiBangCoat liBangCoat = new LiBangCoat();
            // 地砖，东鹏
            DongPengTile dongPengTile = new DongPengTile();

            list.add(levelOneCeiling);
            list.add(liBangCoat);
            list.add(dongPengTile);

            price = price.add(area.multiply(new BigDecimal("0.2")).multiply(levelOneCeiling.price()));
            price = price.add(area.multiply(new BigDecimal("1.4")).multiply(liBangCoat.price()));
            price = price.add(area.multiply(dongPengTile.price()));
        }

        StringBuilder detail = new StringBuilder("-------------------------------------------------------\r\n")
                .append("装修清单 \r\n")
                .append("套餐风格：")
                .append(style)
                .append("\r\n套餐价格：")
                .append(price.setScale(2, RoundingMode.HALF_UP))
                .append(" 元\r\n")
                .append("房屋面积：")
                .append(area.doubleValue())
                .append(" 平米\r\n材料清单：\r\n");

        for (Matter matter : list) {
            detail.append(matter.type())
                    .append("：")
                    .append(matter.brand())
                    .append("、")
                    .append(matter.model())
                    .append("、平米价格：")
                    .append(matter.price())
                    .append(" 元。\n");
        }

        return detail.toString();

    }

}
