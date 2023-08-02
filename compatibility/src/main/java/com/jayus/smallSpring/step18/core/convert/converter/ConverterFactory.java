package com.jayus.smallSpring.step18.core.convert.converter;

/**
 * @author : h zk
 * @date : 2023/8/2 15:01
 * @description :
 **/
public interface ConverterFactory<S,R> {

    <T extends R> Converter<S,T> getConverter(Class<T> targetClass);

}
