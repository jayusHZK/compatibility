package com.jayus.smallSpring.step13.context;

import com.jayus.smallSpring.step13.beans.BeansException;
import com.jayus.smallSpring.step13.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
