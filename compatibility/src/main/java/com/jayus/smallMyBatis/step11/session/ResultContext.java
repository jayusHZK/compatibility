package com.jayus.smallMyBatis.step11.session;

/**
 * @ClassName ResultContext
 * @Description: 结果上下文
 * @date: 2024/5/13 17:56
 */
public interface ResultContext {

    /**
     * 获取结果
     * @return
     */
    Object getResultObject();

    /**
     * 获取记录数
     * @return
     */
    int getResultCount();

}
