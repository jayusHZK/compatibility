package com.jayus.groovy.converter;

/**
 * @ClassName IConverter
 * @Description:
 * @date: 2024/10/24 21:35
 */
public interface IConverter {

    String encoder(String data);

    String decoder(String data);
}

