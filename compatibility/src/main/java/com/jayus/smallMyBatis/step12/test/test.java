package com.jayus.smallMyBatis.step12.test;

import com.jayus.smallMyBatis.step12.io.Resources;
import com.jayus.smallMyBatis.step12.session.SqlSession;
import com.jayus.smallMyBatis.step12.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step12.session.SqlSessionFactoryBuilder;
import com.jayus.smallMyBatis.step12.test.dao.IUserDao;
import com.jayus.smallMyBatis.step12.test.po.User;

import java.io.IOException;

/**
 * @ClassName test
 * @Description: 单元测试
 * @date: 2024/9/26 14:41
 */
public class test {

    private static SqlSession sqlSession;

    public static void main(String[] args) throws Exception {
        init();
        //insert();
        //select();
        //delete();
        //update();
        //selectByUser();
        selectUserList();
    }

    public static void init() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    public static void select(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfoById(1L));
    }

    public static void insert(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.insert(new User("test12"));
        sqlSession.commit();
    }

    public static void delete(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.delete(79L) > 0?"ok":"no");
        sqlSession.commit();
    }

    public static void update(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.update(new User(81L,"test121"));
        sqlSession.commit();
    }

    public static void selectByUser(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfo(new User(1L,"superAdmin")));
    }

    public static void selectUserList(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        for (User u : userDao.queryUserInfoList()) {
            System.out.println(u);
        }
    }

}
