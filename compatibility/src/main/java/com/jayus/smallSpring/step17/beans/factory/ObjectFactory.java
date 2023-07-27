package com.jayus.smallSpring.step17.beans.factory;

import com.jayus.smallSpring.step17.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/27 11:22
 * @description :
 **/
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
