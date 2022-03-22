package cn.ykf.principle.dip.worst;

import cn.ykf.principle.dip.User;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽奖控制类
 *
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class DrawControl {

    /**
     * 随机抽奖
     *
     * @param userList
     * @param count
     * @return
     */
    public List<User> doDrawRandom(List<User> userList, Integer count) {
        if (userList.size() <= count) {
            return userList;
        }

        // 打乱后随机取 count 个用户
        Collections.shuffle(userList);

        return userList.stream().limit(count).collect(Collectors.toList());
    }

    /**
     * 权重抽奖
     *
     * @param userList
     * @param count
     * @return
     */
    public List<User> doDrawWeight(List<User> userList, Integer count) {
        if (userList.size() <= count) {
            return userList;
        }

        // 按照权重排序后抽取 count 个用户
        userList.sort(Comparator.comparingInt(User::getWeight).reversed());
        return userList.stream().limit(count).collect(Collectors.toList());
    }
}
