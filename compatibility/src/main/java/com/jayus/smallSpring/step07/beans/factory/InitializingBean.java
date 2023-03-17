package com.jayus.smallSpring.step07.beans.factory;

/**
 * @author : h zk
 * @date : 2023/3/17 14:13
 * @description : 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出相应的处理
 * 如：执行自定义初始化，或者仅仅检查是否设置及了所有强制属性
 **/
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
