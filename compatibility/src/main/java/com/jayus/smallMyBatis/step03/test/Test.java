package com.jayus.smallMyBatis.step03.test;

import com.jayus.smallMyBatis.step03.dao.IUserDao;
import com.jayus.smallMyBatis.step03.io.Resources;
import com.jayus.smallMyBatis.step03.session.SqlSession;
import com.jayus.smallMyBatis.step03.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step03.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Test {

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // userDao 是代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfoById(1001L));
    }

}
