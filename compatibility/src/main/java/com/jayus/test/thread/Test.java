package com.jayus.test.thread;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/6 23:03
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test::a).start();
    }

    public void a(){
        System.out.println(1);
    }

}