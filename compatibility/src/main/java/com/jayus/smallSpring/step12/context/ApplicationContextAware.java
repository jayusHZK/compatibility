package com.jayus.smallSpring.step12.context;

import com.jayus.smallSpring.step12.beans.BeansException;
import com.jayus.smallSpring.step12.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/4/12 11:38
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
