package com.jayus.smallSpring.step15.context;

import com.jayus.smallSpring.step15.beans.BeansException;
import com.jayus.smallSpring.step15.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/7/10 16:33
 * @description :
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
