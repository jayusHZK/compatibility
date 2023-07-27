package com.jayus.smallSpring.step17.core.convert.converter;

/**
 * @author : h zk
 * @date : 2023/7/27 15:44
 * @description :
 **/
public interface Converter<S,T> {

    T convert(S source);

}
