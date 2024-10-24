package com.jayus.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName test
 * @Description:
 * @date: 2024/10/24 22:03
 */
public class test {

    public static void main(String[] args) throws Exception {
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class<?> ruleClass = gcl.parseClass(new File("D:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\" +
                "jayus\\groovy\\testMain.groovy"));
        if (ruleClass != null){
            GroovyObject o = (GroovyObject) ruleClass.newInstance();
            System.out.println(new String((byte[]) o.invokeMethod("decode", Base64.getEncoder().encode("123".getBytes(StandardCharsets.UTF_8)))));
        }
    }

}
