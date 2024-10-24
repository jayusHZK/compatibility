package com.jayus.rpc_study.step03.test.service.impl;

import com.jayus.rpc_study.step03.test.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @ClassName HelloServiceImpl
 * @Description:
 * @date: 2024/10/22 23:43
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public void echo() {
        System.out.println("rpc ok !!!");
    }
}
