package com.jayus.smallSpring.step12.context.event;

import com.jayus.smallSpring.step12.beans.BeansException;
import com.jayus.smallSpring.step12.beans.factory.BeanFactory;
import com.jayus.smallSpring.step12.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step12.context.ApplicationEvent;
import com.jayus.smallSpring.step12.context.ApplicationListener;
import com.jayus.smallSpring.step12.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/4/12 14:14
 * @description :
 **/
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListenres = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListenres.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListenres.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listenre : applicationListenres) {
            if (supportEvent(listenre,event)) allListeners.add(listenre);
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
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
