package cn.ykf.principle.dip.better;

import cn.ykf.principle.dip.User;
import java.util.List;

/**
 * @author YuKaiFan
 * @date 2022/3/23
 */
public class DrawControl {

    /** 依赖抽象的抽奖接口 */
    private Draw draw;

    public List<User> doDraw(Draw draw, List<User> userList, Integer count) {
        return draw.doDraw(userList, count);
    }

}
