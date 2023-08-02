package com.jayus.smallSpring.step18.core.convert.converter;

/**
 * @author : h zk
 * @date : 2023/8/2 15:03
 * @description :
 **/
public interface ConverterRegistry {

    void addConverter(Converter<?,?> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?,?> converterFactory);

}
