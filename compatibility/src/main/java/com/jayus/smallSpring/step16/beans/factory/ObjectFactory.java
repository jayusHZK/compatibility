package com.jayus.smallSpring.step16.beans.factory;

import com.jayus.smallSpring.step16.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/7/14 11:30
 * @description :
 **/
public interface ObjectFactory<T>{

    T getObject() throws BeansException;

}
