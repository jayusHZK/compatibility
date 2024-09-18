package com.jayus.smallMyBatis.step11.session;

/**
 * @ClassName SqlSession
 * @Description: SqlSession 用来执行 SQL，获取映射器，管理事务
 * @date: 2024/5/13 15:40
 */
public interface SqlSession {

    /**
     * 根据指定的 sqlID 获取一条记录的封装对象
     *
     * @param statement
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的 SqlID 获取一条记录的封装对象，只不过这个方法容许我们可以给sql传递一些参数
     * 一般在实际使用中，这个参数传递的是pojo 或者map 或者 ImmutableMap
     *
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 得到配置
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * 得到映射器，这个巧妙的使用了泛型，使得类型安全
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> type);

}
