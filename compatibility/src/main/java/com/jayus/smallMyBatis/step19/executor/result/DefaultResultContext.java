package com.jayus.smallMyBatis.step19.executor.result;

import com.jayus.smallMyBatis.step19.session.ResultContext;

/**
 * @ClassName DefaultResultContext
 * @Description: 默认结果上下文
 * @date: 2024/10/18 20:26
 */
public class DefaultResultContext implements ResultContext {

    private Object resultObject;

    private int resultCount;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    @Override
    public Object getResultObject() {
        return resultObject;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }

    public void nextResultObject(Object resultObject){
        resultCount++;
        this.resultObject = resultObject;
    }
}
