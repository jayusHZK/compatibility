package com.jayus.smallSpring.step10.context.event;

import com.jayus.smallSpring.step10.beans.BeansException;
import com.jayus.smallSpring.step10.beans.factory.BeanFactory;
import com.jayus.smallSpring.step10.beans.factory.BeanFactoryAware;
import com.jayus.smallSpring.step10.context.ApplicationEvent;
import com.jayus.smallSpring.step10.context.ApplicationListener;
import com.jayus.smallSpring.step10.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/4/3 15:14
 * @description :
 **/
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    // 监听器集合
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        applicationListeners.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
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
        Class<?> enentClassName;
        try {
            enentClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        return enentClassName.isAssignableFrom(event.getClass());
    }

}
