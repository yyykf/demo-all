package cn.ykf.mybatis.mapper;

import cn.ykf.mybatis.constants.UserSex;
import cn.ykf.mybatis.model.User;
import cn.ykf.mybatis.query.UserQuery;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void selectBySex() {
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            final SqlSession sqlSession = factory.openSession();
            final UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            final UserQuery query = new UserQuery();
//            query.setId(41);
            query.setSex(UserSex.MALE);

            final List<User> users = mapper.selectBySex(query);
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}