package com.jayus.smallMyBatis.step08.test;

import com.jayus.smallMyBatis.step08.dao.IUserDao;
import com.jayus.smallMyBatis.step08.io.Resources;
import com.jayus.smallMyBatis.step08.session.SqlSession;
import com.jayus.smallMyBatis.step08.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step08.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfoById(1L));
    }

}
