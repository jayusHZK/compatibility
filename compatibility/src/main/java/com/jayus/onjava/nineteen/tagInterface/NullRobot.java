package com.jayus.onjava.nineteen.tagInterface;

import java.lang.reflect.Proxy;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/8 11:25
 * @description :
 **/
public class NullRobot {
    public static Robot newNullRobot(Class<? extends Robot> type){
        return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader()
        ,new Class[]{NullInterface.class,Robot.class},
                new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {
        Stream.of(new SnowRemovaRobot("SnowBee"),newNullRobot(SnowRemovaRobot.class))
                .forEach(Robot::test);
    }
}
