package com.jayus.smallSpring.step17.core.convert.support;

import com.jayus.smallSpring.step17.core.convert.converter.ConverterRegistry;

/**
 * @author : h zk
 * @date : 2023/7/27 16:04
 * @description :
 **/
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}
