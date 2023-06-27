package com.jayus.smallSpring.step14.beans.factory;

import com.jayus.smallSpring.step14.beans.BeansException;
import com.jayus.smallSpring.step14.beans.PropertyValue;
import com.jayus.smallSpring.step14.beans.PropertyValues;
import com.jayus.smallSpring.step14.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step14.beans.factory.config.BeanFactoryPostProcessor;
import com.jayus.smallSpring.step14.core.io.DefaultResourceLoader;
import com.jayus.smallSpring.step14.core.io.Resource;
import com.jayus.smallSpring.step14.util.StringValueResolver;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;

import java.io.IOException;
import java.util.Properties;

/**
 * @author : h zk
 * @date : 2023/6/27 11:36
 * @description :
 **/
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    value = resolvePlaceholder((String) value,properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),value));
                }
            }
            // 向容器中添加字符串解析器，供解析@Value注解使用
            PlaceholderResolvingStringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startInx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopInx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startInx != -1 && stopInx != -1 && stopInx < stopInx) {
            String propKey = strVal.substring(startInx + 2, stopInx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startInx, stopInx + 1, propVal);
        }
        return buffer.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {
        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolverStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal,properties);
        }
    }
}
