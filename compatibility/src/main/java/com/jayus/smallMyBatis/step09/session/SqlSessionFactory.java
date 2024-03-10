package com.jayus.smallMyBatis.step09.session;

/**
 * 工厂模式接口 构建 SqlSession 的工厂
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
