package com.jayus.onjava.nineteen.tagInterface;

import java.util.*;

/**
 * @author : h zk
 * @date : 2022/8/8 11:00
 * @description :
 **/
public class SnowRemovaRobot implements Robot{

    private String name;

    public SnowRemovaRobot(String name){
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "SnowBot Series 11";
    }

    private List<Operation> ops = Arrays.asList(
            new Operation(() -> name + " can shovel snow",() -> System.out.println(name + " shoveling snow; " + Thread.currentThread().getName())),
            new Operation(() -> name + " can ship ice",() -> System.out.println(name + " chipping ice; " + Thread.currentThread().getName())),
            new Operation(() -> name + " can clear the roof",() -> System.out.println(name + " clearing roof; " + Thread.currentThread().getName()))
    );

    @Override
    public List<Operation> operations() {
        return ops;
    }

    public static void main(String[] args) {
        Robot.test(new SnowRemovaRobot("Slusher"));
        System.out.println("---------");
        for (Map.Entry<String, String> item : System.getenv().entrySet()) {
            System.out.println(item.getKey()+": " +item.getValue());
        }
    }
}
