package com.jayus.smallSpring.step10.beans.factory.config;

import com.jayus.smallSpring.step10.beans.PropertyValues;

/**
 * @author : h zk
 * @date : 2023/3/31 15:41
 * @description :
 **/
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destoryMethodName;

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this(beanClass,null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ?propertyValues:new PropertyValues();
    }

    public boolean isSingleton(){
        return singleton;
    }

    public boolean isPrototype(){
        return prototype;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestoryMethodName() {
        return destoryMethodName;
    }

    public void setDestoryMethodName(String destoryMethodName) {
        this.destoryMethodName = destoryMethodName;
    }
}
