package cn.ykf.mybatis.mapper;

import cn.ykf.mybatis.model.User;
import cn.ykf.mybatis.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YuKaiFan
 * @date 2022/3/13
 */
public interface UserMapper {

    List<User> selectBySex(@Param("query") UserQuery query);
}
