package com.jayus.smallMyBatis.step09.test;

import com.jayus.smallMyBatis.step09.dao.IUserDao;
import com.jayus.smallMyBatis.step09.io.Resources;
import com.jayus.smallMyBatis.step09.po.User;
import com.jayus.smallMyBatis.step09.session.SqlSession;
import com.jayus.smallMyBatis.step09.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step09.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        //test_queryUserInfoById();
        test_queryUserInfo();
    }

    public static void test_queryUserInfoById() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfoById(1));
    }

    public static void test_queryUserInfo() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1);
        user.setUserId("bb");
        System.out.println(userDao.queryUserInfo(user));
    }

}
