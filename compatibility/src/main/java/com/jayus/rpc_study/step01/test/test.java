package com.jayus.rpc_study.step01.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName test
 * @Description:
 * @date: 2024/10/22 13:26
 */
public class test {

    public static void main(String[] args) {
        String[] configs = {"rpc/rpc-provider.xml","rpc/rpc-consumer.xml"};
        new ClassPathXmlApplicationContext(configs);
    }

}
