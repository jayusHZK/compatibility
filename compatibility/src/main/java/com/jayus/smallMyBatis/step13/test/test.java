package com.jayus.smallMyBatis.step13.test;

import com.jayus.smallMyBatis.step13.io.Resources;
import com.jayus.smallMyBatis.step13.session.SqlSession;
import com.jayus.smallMyBatis.step13.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step13.session.SqlSessionFactoryBuilder;
import com.jayus.smallMyBatis.step13.test.dao.IUserDao;
import com.jayus.smallMyBatis.step13.test.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @ClassName test
 * @Description: 测试用例
 * @date: 2024/10/16 10:47
 */
public class test {

    private Logger logger = LoggerFactory.getLogger(test.class);

    private static SqlSession sqlSession;

    public static void main(String[] args) throws IOException {
        init();
        //insert();
        //update();
        //delete();
        //select();
        //selectBy();
        selectList();
    }

    public static void init() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    public static void insert() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.insert(new User("test13"));
        sqlSession.commit();
    }

    public static void update(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.update(new User(83L, "test13_2")));
        sqlSession.commit();
    }

    public static void delete(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.delete(83L));
        sqlSession.commit();
    }

    public static void select(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfoById(1L));
    }

    public static void selectBy(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.queryUserInfo(new User(14L, "admin")));
    }

    public static void selectList(){
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        for (User user : userDao.queryUserInfoList()) {
            System.out.println(user);
        }
    }

}
