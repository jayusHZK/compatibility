package com.jayus.smallMyBatis.step19.test;

import com.jayus.smallMyBatis.step19.session.SqlSession;
import com.jayus.smallMyBatis.step19.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step19.session.SqlSessionFactoryBuilder;
import com.jayus.smallMyBatis.step19.io.Resources;
import com.jayus.smallMyBatis.step19.test.dao.IUserDao;
import com.jayus.smallMyBatis.step19.test.po.User;

import java.io.IOException;
import java.io.Reader;

/**
 * @ClassName test
 * @Description: 测试用例
 * @date: 2024/10/20 13:25
 */
public class test {

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        User user = new User();
        user.setId(1L);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryById(user));
    }

}
