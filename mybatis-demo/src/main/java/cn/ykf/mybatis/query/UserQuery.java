package cn.ykf.mybatis.query;

import cn.ykf.mybatis.constants.UserSex;

/**
 * @author YuKaiFan
 * @date 2022/3/13
 */
public class UserQuery {

    private Integer id;
    private UserSex sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }
}
