package com.jayus.smallSpring.step12.beans.factory;

/**
 * @Author: h zk
 * @Description: 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出响应的处理，
 *  如：执行自定义初始化，或者检查是否设置了所有强制属性
 * @Date: 2023/4/9 18:11
 * @Version: 1.0
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
