package com.jayus.smallSpring.step18.core.convert.converter;

/**
 * @author : h zk
 * @date : 2023/8/2 14:59
 * @description :
 **/
public interface Converter<S,T> {

    T convert(S source);

}
