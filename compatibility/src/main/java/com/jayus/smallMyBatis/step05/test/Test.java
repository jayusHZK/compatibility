package com.jayus.smallMyBatis.step05.test;

import com.jayus.smallMyBatis.step05.dao.IUserDao;
import com.jayus.smallMyBatis.step05.datasource.pooled.PooledDataSource;
import com.jayus.smallMyBatis.step05.io.Resources;
import com.jayus.smallMyBatis.step05.session.SqlSession;
import com.jayus.smallMyBatis.step05.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step05.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.sql.Connection;

public class Test {

    public static void main(String[] args) throws Exception {
        //test_SqlSessionFactory();
        test_pooled();
    }

    public static void test_SqlSessionFactory() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        for (int i = 0; i < 50; i++) {
            System.out.println(userDao.queryUserInfoById(1L));
        }
    }

    public static void test_pooled() throws Exception{
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/world?useUnicode=true&useSSL=false&characterEncoding=utf-8");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123456");

        while (true){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }

}
