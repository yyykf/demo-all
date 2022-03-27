package cn.ykf.builder.better;

import cn.ykf.builder.common.Matter;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public interface IMenu {

    IMenu appendCeiling(Matter ceiling);

    IMenu appendCoat(Matter coat);

    IMenu appendFloor(Matter floor);

    IMenu appendTile(Matter tile);

    String getDetail();
}
