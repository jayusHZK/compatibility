package com.jayus.smallSpring.step17.test.converter;

import com.jayus.smallSpring.step17.core.convert.converter.Converter;

/**
 * @author : h zk
 * @date : 2023/8/1 17:46
 * @description :
 **/
public class StringToIntegerConverter implements Converter<String,Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
