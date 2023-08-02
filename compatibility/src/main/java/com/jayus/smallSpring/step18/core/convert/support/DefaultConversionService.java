package com.jayus.smallSpring.step18.core.convert.support;

import com.jayus.smallSpring.step18.core.convert.converter.ConverterRegistry;

/**
 * @author : h zk
 * @date : 2023/8/2 16:27
 * @description :
 **/
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry){
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
