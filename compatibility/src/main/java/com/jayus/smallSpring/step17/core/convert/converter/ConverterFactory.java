package com.jayus.smallSpring.step17.core.convert.converter;

/**
 * @author : h zk
 * @date : 2023/7/27 15:45
 * @description :
 **/
public interface ConverterFactory<S,R> {

    <T extends R> Converter<S,T> getConverter(Class<T> targetType);

}
