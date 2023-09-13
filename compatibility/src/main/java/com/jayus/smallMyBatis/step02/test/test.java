package com.jayus.smallMyBatis.step02.test;

import com.jayus.smallMyBatis.step02.binding.MapperRegistry;
import com.jayus.smallMyBatis.step02.session.SqlSession;
import com.jayus.smallMyBatis.step02.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step02.session.defaults.DefaultSqlSessionFactory;
import com.jayus.smallMyBatis.step02.test.dao.IUserDao;

public class test {

    public static void main(String[] args) {
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.jayus.smallMyBatis.step02.test.dao");
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserName("1001"));
    }

}
