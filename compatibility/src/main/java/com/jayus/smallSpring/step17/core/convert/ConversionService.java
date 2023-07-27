package com.jayus.smallSpring.step17.core.convert;

import javax.annotation.Nullable;

/**
 * @author : h zk
 * @date : 2023/7/27 15:29
 * @description :
 **/
public interface ConversionService {

    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source,Class<?> targetType);

}
