package cn.ykf.principle.srp.better.impl;

import cn.ykf.principle.srp.better.IVideoUserService;

/**
 * 普通视频用户服务
 *
 * @author YuKaiFan
 * @date 2022/3/20
 */
public class OrdinaryVideoUserService implements IVideoUserService {

    @Override
    public void definition() {
        System.out.println("普通用户，清晰度720P");
    }

    @Override
    public void advertisement() {
        System.out.println("普通用户，有广告");
    }
}
