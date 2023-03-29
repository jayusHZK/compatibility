package com.jayus.smallSpring.step09.context;

import com.jayus.smallSpring.step09.beans.BeansException;
import com.jayus.smallSpring.step09.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/3/29 14:30
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
