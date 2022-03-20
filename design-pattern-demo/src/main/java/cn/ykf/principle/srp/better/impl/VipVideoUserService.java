package cn.ykf.principle.srp.better.impl;

import cn.ykf.principle.srp.better.IVideoUserService;

/**
 * VIP视频用户服务
 *
 * @author YuKaiFan
 * @date 2022/3/20
 */
public class VipVideoUserService implements IVideoUserService {

    @Override
    public void definition() {
        System.out.println("VIP用户，清晰度1080P");
    }

    @Override
    public void advertisement() {
        System.out.println("VIP用户，无广告");
    }
}
