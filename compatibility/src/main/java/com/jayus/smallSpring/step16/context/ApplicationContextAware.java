package com.jayus.smallSpring.step16.context;

import com.jayus.smallSpring.step16.beans.BeansException;
import com.jayus.smallSpring.step16.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/7/17 15:17
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
