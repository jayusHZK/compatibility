package com.jayus.smallSpring.step18.core.convert.support;

import com.jayus.smallSpring.step18.core.convert.converter.Converter;
import com.jayus.smallSpring.step18.core.convert.converter.ConverterFactory;
import com.jayus.smallSpring.step18.util.NumberUtils;

/**
 * @author : h zk
 * @date : 2023/8/2 16:29
 * @description :
 **/
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetClass) {
        return new StringToNumber<>(targetClass);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()){
                return null;
            }
            return NumberUtils.parseNumber(source,this.targetType);
        }
    }

}
