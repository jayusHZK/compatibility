package com.jayus.smallMyBatis.step13.session;

/**
 * @ClassName SqlSessionFactory
 * @Description: 工厂模式接口，构建SqlSession 的工厂
 * @date: 2024/10/12 16:13
 */
public interface SqlSessionFactory {

    /*
    打开一个 session
     */
    SqlSession openSession();

}
