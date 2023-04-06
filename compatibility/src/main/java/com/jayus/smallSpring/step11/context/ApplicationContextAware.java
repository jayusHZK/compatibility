package com.jayus.smallSpring.step11.context;

import com.jayus.smallSpring.step11.beans.BeansException;
import com.jayus.smallSpring.step11.beans.factory.Aware;

/**
 * @author : h zk
 * @date : 2023/4/6 17:15
 * @description : 实现此接口，即能感知到所属的 ApplicationContext
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
