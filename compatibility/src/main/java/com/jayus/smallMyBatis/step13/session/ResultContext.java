package com.jayus.smallMyBatis.step13.session;

/**
 * @ClassName ResultContext
 * @Description: 结构上下文
 * @date: 2024/10/12 16:14
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
