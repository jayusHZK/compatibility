package com.jayus.smallSpring.step18.beans.factory;

import com.jayus.smallSpring.step18.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/8/2 17:34
 * @description :
 **/
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
