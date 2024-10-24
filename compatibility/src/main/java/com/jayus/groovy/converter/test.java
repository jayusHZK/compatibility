package com.jayus.groovy.converter;

import groovy.lang.GroovyClassLoader;

import java.io.File;

/**
 * @ClassName test
 * @Description:
 * @date: 2024/10/24 21:39
 */
public class test {

    public static void main(String[] args) throws Exception {
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class<?> ruleClass = gcl.parseClass(new File("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\" +
                "jayus\\groovy\\converter\\impl\\Base64Converter.groovy"));
        if (ruleClass != null){
            IConverter converter = (IConverter) ruleClass.newInstance();
            String encoder = converter.encoder("123");
            System.out.println(encoder);
            System.out.println(converter.decoder(encoder));
        }
    }

}
