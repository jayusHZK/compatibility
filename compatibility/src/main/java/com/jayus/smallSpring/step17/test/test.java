package com.jayus.smallSpring.step17.test;

import com.jayus.smallSpring.step17.context.support.ClassPathXmlApplicationContext;
import com.jayus.smallSpring.step17.test.bean.Husband;
import com.jayus.smallSpring.step17.test.converter.StringToIntegerConverter;

/**
 * @author : h zk
 * @date : 2023/8/1 17:55
 * @description :
 **/
public class test {

    public static void main(String[] args) {
        test_convert();
        test_StringToIntegerConverter();
    }

    public static void test_convert(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println(husband);
    }

    public static void test_StringToIntegerConverter(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        System.out.println(converter.convert("1234"));
    }

}
