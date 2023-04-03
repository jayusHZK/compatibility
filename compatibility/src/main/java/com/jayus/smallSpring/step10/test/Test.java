package com.jayus.smallSpring.step10.test;

import com.jayus.smallSpring.step10.context.support.ClassPathXmlApplicationContext;
import com.jayus.smallSpring.step10.event.CustomEvent;

/**
 * @author : h zk
 * @date : 2023/4/3 17:55
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext,1019L,"成功了"));
        applicationContext.registerShutdownHook();
    }

}
