package com.jayus.smallMyBatis.step12.session;

/**
 * @ClassName SqlSessionFactory
 * @Description: 工厂模式接口，构建 SqlSession 的工厂
 * @date: 2024/9/25 08:38
 */
public interface SqlSessionFactory {

    /*
    打开一个 session
     */
    SqlSession openSession();

}
