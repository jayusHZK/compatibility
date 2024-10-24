package com.jayus.rpc_study.step03.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName test
 * @Description:
 * @date: 2024/10/22 23:42
 */
public class test {

    public static void main(String[] args) {
        String[] config = {"rpc/rpc-provider.xml","rpc/rpc-consumer.xml","rpc/rpc-server.xml"};
        new ClassPathXmlApplicationContext(config);
    }

}
