package cn.ykf.builder.better;

import cn.ykf.builder.common.Matter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class DecorationPackageMenu implements IMenu {

    private final List<Matter> list = new ArrayList<>();
    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal area;
    private String style;

    public DecorationPackageMenu() {
    }

    public DecorationPackageMenu(Double area, String style) {
        this.area = BigDecimal.valueOf(area);
        this.style = style;
    }

    @Override
    public IMenu appendCeiling(Matter ceiling) {
        list.add(ceiling);
        price = price.add(area.multiply(new BigDecimal("0.2")).multiply(ceiling.price()));

        return this;
    }

    @Override
    public IMenu appendCoat(Matter coat) {
        list.add(coat);
        price = price.add(area.multiply(new BigDecimal("1.4")).multiply(coat.price()));

        return this;
    }

    @Override
    public IMenu appendFloor(Matter floor) {
        list.add(floor);
        price = price.add(area.multiply(floor.price()));

        return this;
    }

    @Override
    public IMenu appendTile(Matter tile) {
        list.add(tile);
        price = price.add(area.multiply(tile.price()));

        return this;
    }

    @Override
    public String getDetail() {
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
