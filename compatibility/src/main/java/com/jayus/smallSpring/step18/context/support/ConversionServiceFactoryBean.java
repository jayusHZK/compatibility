package com.jayus.smallSpring.step18.context.support;

import com.jayus.smallSpring.step18.beans.factory.FactoryBean;
import com.jayus.smallSpring.step18.beans.factory.InitializingBean;
import com.jayus.smallSpring.step18.core.convert.ConversionService;
import com.jayus.smallSpring.step18.core.convert.converter.Converter;
import com.jayus.smallSpring.step18.core.convert.converter.ConverterFactory;
import com.jayus.smallSpring.step18.core.convert.converter.ConverterRegistry;
import com.jayus.smallSpring.step18.core.convert.converter.GenericConverter;
import com.jayus.smallSpring.step18.core.convert.support.DefaultConversionService;
import com.jayus.smallSpring.step18.core.convert.support.GenericConversionService;

import java.util.Set;

public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjecType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters,conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry){
        if (converters != null){
            for (Object converter : converters) {
                if (converter instanceof GenericConversionService){
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?,?>){
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?,?>){
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
