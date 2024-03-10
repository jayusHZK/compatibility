package com.jayus.test.extendsTest;

/**
 * @author : h zk
 * @date : 2023/3/27 14:28
 * @description :
 **/
public class Son extends FatherOne implements FatherTwo{

    @Override
    public void a() {
        //System.out.println(this.x);
        System.out.println(super.x);
    }

    public static void main(String[] args) {
        new Son().a();
    }

}
