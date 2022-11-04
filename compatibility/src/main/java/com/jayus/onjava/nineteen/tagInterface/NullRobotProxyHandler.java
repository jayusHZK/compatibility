package com.jayus.onjava.nineteen.tagInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : h zk
 * @date : 2022/8/8 11:17
 * @description :
 **/
public class NullRobotProxyHandler implements InvocationHandler {

    private String nullName;

    private Robot proxied = new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type){
        nullName = type.getSimpleName() + " NullRobot";
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied,args);
    }


    private class NRobot implements NullInterface,Robot{
        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }
}
