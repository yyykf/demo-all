package cn.ykf.principle.dip.worst;

import cn.ykf.principle.dip.User;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final Random random = new Random();

        final List<User> users = Arrays.asList(new User("A", random.nextInt(100)),
                new User("B", random.nextInt(100)),
                new User("C", random.nextInt(100)),
                new User("D", random.nextInt(100)),
                new User("E", random.nextInt(100)));
        LOGGER.info("初始用户集合： {}",users);

        DrawControl drawControl = new DrawControl();
        List<User> prizeRandomUserList = drawControl.doDrawRandom(users, 3);
        LOGGER.info("随机抽奖，中奖用户名单：{}", prizeRandomUserList);

        List<User> prizeWeightUserList = drawControl.doDrawWeight(users, 3);
        LOGGER.info("权重抽奖，中奖用户名单：{}", prizeWeightUserList);
    }
}
