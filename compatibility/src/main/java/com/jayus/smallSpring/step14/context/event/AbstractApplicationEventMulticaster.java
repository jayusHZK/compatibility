package com.jayus.smallSpring.step14.context.event;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.factory.BeanFactory;
import com.jayus.smallSpring.step14.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step14.context.ApplicationEvent;
import com.jayus.smallSpring.step14.context.ApplicationListener;
import com.jayus.smallSpring.step14.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/6/26 17:39
 * @description :
 **/
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportEvent(listener,event)) allListeners.add(listener);
        }
        return allListeners;
    }

    protected boolean supportEvent(ApplicationListener<ApplicationEvent> applicationListener,ApplicationEvent event){
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        /**
         * 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口
         * isAssignableFrom 是用来判断子类和父类的关系的，或者接口的实现类和接口的关系，默认所有类的终极父类都是Object。
         * 如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A，也就是A可以由B转换而来。
         */
        return eventClassName.isAssignableFrom(event.getClass());
    }


}
