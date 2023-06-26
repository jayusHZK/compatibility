package com.jayus.smallSpring.step14.context;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/6/26 16:58
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
