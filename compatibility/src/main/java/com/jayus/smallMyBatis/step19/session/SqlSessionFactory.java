package com.jayus.smallMyBatis.step19.session;

/**
 * @ClassName SqlSessionFactory
 * @Description: 工厂模式接口，构建 SqlSession 的工厂
 * @date: 2024/10/18 11:24
 */
public interface SqlSessionFactory {

    /*
    打开一个 session
     */
    SqlSession openSession();

}
