package com.jayus.smallSpring.step08.beans.factory;

import com.jayus.smallSpring.step08.beans.BeansException;

/**
 * @author : h zk
 * @date : 2023/3/23 19:24
 * @description : 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出相应的处理，
 * 如：执行自定义初始化，或者检查是否设置了所有强制属性
 **/
public interface InitializingBean {

    void afterPropertiesSet() throws BeansException;

}
