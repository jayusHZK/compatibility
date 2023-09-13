package com.jayus.test.reflectTest;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClassTest {

    public String s;

    public static void main(String[] args) {

        System.out.println(String.class.isMemberClass());
        System.out.println(test.class.getSuperclass());
        for (Class<?> clazz : test.class.getInterfaces()) {
            System.out.println(clazz.getName());
        }
    }

    public class test extends ThreadPoolExecutor implements Serializable, Closeable  {

        public String s;

        public test(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public void close() throws IOException {

        }
    }

}
