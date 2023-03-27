package com.jayus.smallSpring.step08.context;

import com.jayus.smallSpring.step08.beans.BeansException;
import com.jayus.smallSpring.step08.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/3/27 14:59
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
