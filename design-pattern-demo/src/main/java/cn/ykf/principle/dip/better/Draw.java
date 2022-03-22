package cn.ykf.principle.dip.better;

import cn.ykf.principle.dip.User;
import java.util.List;

/**
 * 抽奖接口
 *
 * @author YuKaiFan
 * @date 2022/3/23
 */
public interface Draw {

    /**
     * 抽奖
     *
     * @param userList
     * @param count
     * @return
     */
    List<User> doDraw(List<User> userList, Integer count);
}
