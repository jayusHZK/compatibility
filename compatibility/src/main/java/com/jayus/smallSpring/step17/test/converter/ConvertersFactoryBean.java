package com.jayus.smallSpring.step17.test.converter;

import com.jayus.smallSpring.step17.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/8/1 17:41
 * @description :
 **/
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

    @Override
    public Set<?> getObject() throws Exception {
        HashSet<Object> converters = new HashSet<>();
        StringToLocalDataConverter stringToLocalDataConverter = new StringToLocalDataConverter("yyyy-MM-dd");
        converters.add(stringToLocalDataConverter);
        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
