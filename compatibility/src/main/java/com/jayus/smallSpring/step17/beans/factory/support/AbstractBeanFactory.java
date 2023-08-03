package com.jayus.smallSpring.step17.beans.factory.support;

import com.jayus.smallSpring.step17.beans.BeansException;
import com.jayus.smallSpring.step17.beans.factory.FactoryBean;
import com.jayus.smallSpring.step17.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step17.beans.factory.config.BeanPostProcessor;
import com.jayus.smallSpring.step17.beans.factory.config.ConfigurableBeanFactory;
import com.jayus.smallSpring.step17.core.convert.ConversionService;
import com.jayus.smallSpring.step17.util.ClassUtils;
import com.jayus.smallSpring.step17.util.StringValueResolver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/7/28 14:00
 * @description :
 **/
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private ConversionService conversionService;

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    /**
     * 获取 bean 如 bean 未实例化，则先获取 BeanDefinition 去实例化
     * @param name
     * @param args
     * @return
     * @param <T>
     */
    protected <T> T doGetBean(final String name, final Object[] args){
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null){
            return (T) getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        if (!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        //
        Object object = getCacheObjectForFactoryBean(beanName);

        if (object == null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Nullable
    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
