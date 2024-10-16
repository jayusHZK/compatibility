package com.jayus.smallMyBatis.step12.executor.result;

import com.jayus.smallMyBatis.step12.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step12.session.ResultContext;
import com.jayus.smallMyBatis.step12.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DefaultResultHandler
 * @Description: 默认结果处理器
 * @date: 2024/9/25 22:11
 */
public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }

    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handlerResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList(){
        return list;
    }

}
