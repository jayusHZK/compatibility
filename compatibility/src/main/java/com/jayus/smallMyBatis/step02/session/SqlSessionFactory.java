package com.jayus.smallMyBatis.step02.session;

public interface SqlSessionFactory {

    /**
     * 打开一个新的 SqlSession 对象
     * @return
     */
    SqlSession openSession();

}
