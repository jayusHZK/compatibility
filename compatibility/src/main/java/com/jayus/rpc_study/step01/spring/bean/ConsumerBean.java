package com.jayus.rpc_study.step01.spring.bean;

import com.jayus.rpc_study.step01.ConsumerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName ConsumerBean
 * @Description:
 * @date: 2024/10/22 13:17
 */
public class ConsumerBean<T> extends ConsumerConfig<T> implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return refer();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
