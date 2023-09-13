package com.jayus.spi.logger;

public class MyLogBack implements Logger {

    @Override
    public void info(String msg) {
        System.out.println("MyLogBack 的 info 输出" + msg);
    }

    @Override
    public void debug(String msg) {
        System.out.println("MyLogBack 的 debug 输出" + msg);
    }

}
