package com.jayus.smallMyBatis.step12.session;

/**
 * @ClassName ResultContext
 * @Description: 结果上下文
 * @date: 2024/9/25 08:30
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
