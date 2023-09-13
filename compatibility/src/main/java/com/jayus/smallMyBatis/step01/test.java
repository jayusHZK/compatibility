package com.jayus.smallMyBatis.step01;

import com.jayus.smallMyBatis.step01.binding.MapperProxyFactory;
import com.jayus.smallMyBatis.step01.dao.IUserDao;

import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String,String> sqlSession = new HashMap<>();
        IUserDao userDao = mapperProxyFactory.newInstance(sqlSession);
        System.out.println(userDao.queryUserName("1001"));
    }

}
