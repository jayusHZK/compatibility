package com.jayus.onjava.thirteen.methodReference;

import java.util.function.Consumer;

/**
 * @author : h zk
 * @date : 2022/7/19 9:54
 * @description :
 **/
public class MethodReference {
    static void hello(String name) {
        System.out.println("hello ," + name);
    }

    static class Description {
        String about;

        Description(String desc) {
            about = desc;
        }

        void help(String msg) {
            System.out.println(about + " " + msg);
        }
    }

    static class Helper {
        static void assist(String msg){
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Describe d = new Describe();
        Callable c = d::show;
        c.call("call()");

        c = MethodReference::hello;
        c.call("job");

        c = new Description("bod")::help;
        c.call("123");

        c = Helper::assist;
        c.call("help");
    }
}
