package com.jayus.smallSpring.step02.factory;

import com.jayus.smallSpring.step02.exception.BeanException;

public interface BeanFactory {

    Object getBean(String name) throws BeanException;

}
