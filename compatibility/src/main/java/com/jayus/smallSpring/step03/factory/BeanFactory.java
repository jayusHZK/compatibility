package com.jayus.smallSpring.step03.factory;

import com.jayus.smallSpring.step03.exception.BeanException;

public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    Object getBean(String name,Object... args) throws BeanException;

}
