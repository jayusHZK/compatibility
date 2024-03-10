package com.jayus.test.extendsTest;

/**
 * @author : h zk
 * @date : 2023/3/27 14:25
 * @description :
 **/
public class FatherOne extends FatherThree{

    int x = 1;

    public FatherOne() {
        System.out.println("father1");
    }

    public void a(){
        System.out.println("b");
    }

    public void b(){
        System.out.println("b");
    }

}
