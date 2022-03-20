package cn.ykf.principle.srp.better.impl;

import cn.ykf.principle.srp.better.IVideoUserService;

/**
 * 访问视频用户服务
 *
 * @author YuKaiFan
 * @date 2022/3/20
 */
public class GuestVideoUserService implements IVideoUserService {

    @Override
    public void definition() {
        System.out.println("访客用户，清晰度480P");
    }

    @Override
    public void advertisement() {
        System.out.println("访客用户，有广告");
    }
}
