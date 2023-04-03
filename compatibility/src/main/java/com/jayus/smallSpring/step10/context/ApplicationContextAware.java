package com.jayus.smallSpring.step10.context;

import com.jayus.smallSpring.step10.beans.BeansException;
import com.jayus.smallSpring.step10.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/4/3 14:29
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
