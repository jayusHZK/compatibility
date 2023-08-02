package com.jayus.smallSpring.step18.core.convert;

/**
 * @author : h zk
 * @date : 2023/8/2 14:55
 * @description :
 **/
public interface ConversionService {

    boolean canConvert(Class<?> sourceType,Class<?> targetType);

    <T> T convert(Object source,Class<T> targetType);

}
