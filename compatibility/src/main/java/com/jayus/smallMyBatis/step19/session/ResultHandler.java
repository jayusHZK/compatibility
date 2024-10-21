package com.jayus.smallMyBatis.step19.session;

/**
 * @ClassName ResultHandler
 * @Description: 结果处理器
 * @date: 2024/10/18 11:26
 */
public interface ResultHandler {

    /*
    处理结果
     */
    void handleResult(ResultContext context);

}
