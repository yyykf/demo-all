package cn.ykf.principle.srp.better;

/**
 * 视频用户服务接口
 *
 * @author YuKaiFan
 * @date 2022/3/20
 */
public interface IVideoUserService {
    /*
        单一职责，每个实现类实现各自的业务逻辑
     */

    /** 清晰度 */
    void definition();

    /** 广告 */
    void advertisement();
}
