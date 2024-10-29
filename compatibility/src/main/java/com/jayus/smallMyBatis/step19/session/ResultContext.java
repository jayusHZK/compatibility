package com.jayus.smallMyBatis.step19.session;

/**
 * @ClassName ResultContext
 * @Description: 结果上下文
 * @date: 2024/10/18 11:25
 */
public interface ResultContext {

    /*
    获取结果
     */
    Object getResultObject();

    /*
    获取记录数
     */
    int getResultCount();

}