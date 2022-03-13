package cn.ykf.mybatis.constants;

/**
 * @author YuKaiFan
 * @date 2022/3/13
 */
public enum UserSex {
    MALE("男"),
    FEMALE("女");

    private final String sex;

    UserSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
