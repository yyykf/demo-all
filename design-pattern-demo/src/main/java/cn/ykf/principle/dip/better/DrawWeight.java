package cn.ykf.principle.dip.better;

import cn.ykf.principle.dip.User;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YuKaiFan
 * @date 2022/3/23
 */
public class DrawWeight implements Draw{

    @Override
    public List<User> doDraw(List<User> userList, Integer count) {
        if (userList.size() <= count) {
            return userList;
        }

        // 按照权重排序后抽取 count 个用户
        userList.sort(Comparator.comparingInt(User::getWeight).reversed());
        return userList.stream().limit(count).collect(Collectors.toList());
    }
}
