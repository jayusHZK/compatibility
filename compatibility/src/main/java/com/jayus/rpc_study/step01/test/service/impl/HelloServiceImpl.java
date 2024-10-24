package com.jayus.rpc_study.step01.test.service.impl;

import com.jayus.rpc_study.step01.test.service.HelloService;

/**
 * @ClassName HelloServiceImpl
 * @Description:
 * @date: 2024/10/22 13:23
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void echo() {
        System.out.println("hi itstack demo rpc");
    }
}
