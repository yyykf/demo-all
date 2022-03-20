package cn.ykf.principle.srp.worst;

/**
 * 视频会员服务
 *
 * @author YuKaiFan
 * @date 2022/3/20
 */
public class VideoUserService {

    /** 违背了单一职责 */
    public void serverGrade(String userType) {
        if ("VIP".equals(userType)) {
            System.out.println("会员用户，清晰度1080P");
            System.out.println("会员用户，无广告");
        } else if ("Ordinary".equals(userType)) {
            System.out.println("会员用户，清晰度720P");
            System.out.println("会员用户，有广告");
        } else if ("Guest".equals(userType)) {
            System.out.println("会员用户，清晰度4880P");
            System.out.println("会员用户，有广告");
        }
    }

    public static void main(String[] args) {
        final VideoUserService videoUserService = new VideoUserService();
        videoUserService.serverGrade("Ordinary");
    }
}
