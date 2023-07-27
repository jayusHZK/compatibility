package com.jayus.smallSpring.step17.core.convert.converter;

/**
 * @author : h zk
 * @date : 2023/7/27 15:46
 * @description :
 **/
public interface ConverterRegistry {

    void addConverter(Converter<?,?> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?,?> converterFactory);

}
