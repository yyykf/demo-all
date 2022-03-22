package cn.ykf.principle.dip.better;

import cn.ykf.principle.dip.User;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YuKaiFan
 * @date 2022/3/23
 */
public class DrawRandom implements Draw {

    @Override
    public List<User> doDraw(List<User> userList, Integer count) {
        if (userList.size() <= count) {
            return userList;
        }

        // 打乱后随机取 count 个用户
        Collections.shuffle(userList);

        return userList.stream().limit(count).collect(Collectors.toList());
    }
}
