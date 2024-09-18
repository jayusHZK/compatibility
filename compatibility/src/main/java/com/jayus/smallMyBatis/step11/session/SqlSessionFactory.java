package com.jayus.smallMyBatis.step11.session;

/**
 * @ClassName SqlSessionFactory
 * @Description: 工厂模式接口，构建SqlSession 的工厂
 * @date: 2024/5/13 17:58
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     * @return
     */
    SqlSession openSession();

}
