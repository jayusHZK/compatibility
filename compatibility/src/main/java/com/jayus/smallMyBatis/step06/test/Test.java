package com.jayus.smallMyBatis.step06.test;

import com.jayus.smallMyBatis.step06.session.SqlSession;
import com.jayus.smallMyBatis.step06.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step06.session.SqlSessionFactoryBuilder;
import com.jayus.smallMyBatis.step06.dao.IUserDao;
import com.jayus.smallMyBatis.step06.io.Resources;

public class Test {

    public static void main(String[] args) throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        System.out.println(userDao.queryUserInfoById(1));
    }

}
