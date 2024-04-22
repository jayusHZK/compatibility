package com.jayus.smallMyBatis.step10.test;

import com.jayus.smallMyBatis.step10.io.Resources;
import com.jayus.smallMyBatis.step10.session.SqlSession;
import com.jayus.smallMyBatis.step10.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step10.session.SqlSessionFactoryBuilder;
import com.jayus.smallMyBatis.step10.test.dao.IUserDao;
import com.jayus.smallMyBatis.step10.test.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    private SqlSession sqlSession;

    public static void main(String[] args) throws IOException {
        ApiTest apiTest = new ApiTest();
        apiTest.init();
        //apiTest.test_queryUserInfoById();
        apiTest.test_queryUserInfo();
    }


    public void init() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    public void test_queryUserInfoById(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = userDao.queryUserInfoById(1L);
        System.out.println(user);
    }

    public void test_queryUserInfo(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1L);
        user.setUser_name("admin");
        System.out.println(userDao.queryUserInfo(user));
    }


}
