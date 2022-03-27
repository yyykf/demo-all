package cn.ykf.builder.better;

import cn.ykf.builder.common.ceiling.LevelOneCeiling;
import cn.ykf.builder.common.ceiling.LevelTwoCeiling;
import cn.ykf.builder.common.coat.DuluxCoat;
import cn.ykf.builder.common.coat.LiBangCoat;
import cn.ykf.builder.common.floor.ShengXiangFloor;
import cn.ykf.builder.common.tile.DongPengTile;
import cn.ykf.builder.common.tile.MarcoPoloTile;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class Builder {

    public IMenu levelOne(Double area) {
        return new DecorationPackageMenu(area, "豪华欧式")
                // 吊顶，二级顶
                .appendCeiling(new LevelTwoCeiling())
                // 涂料，多乐士
                .appendCoat(new DuluxCoat())
                // 地板，圣象
                .appendFloor(new ShengXiangFloor());
    }

    public IMenu levelTwo(Double area) {
        return new DecorationPackageMenu(area, "轻奢田园")
                // 吊顶，二级顶
                .appendCeiling(new LevelTwoCeiling())
                // 涂料，立邦
                .appendCoat(new LiBangCoat())
                // 地砖，马可波罗
                .appendTile(new MarcoPoloTile());
    }

    public IMenu levelThree(Double area) {
        return new DecorationPackageMenu(area, "现代简约")
                // 吊顶，一级顶
                .appendCeiling(new LevelOneCeiling())
                // 涂料，立邦
                .appendCoat(new LiBangCoat())
                // 地砖，东鹏
                .appendTile(new DongPengTile());
    }


}
