package com.jayus.onjava.function.CtorReference;

/**
 * @author : h zk
 * @date : 2022/7/19 11:14
 * @description :
 **/
public class Dog {
    String name;
    int age = -1;
    Dog(){
        name = "stray";
    }
    Dog(String nm,int yrs){
        name = nm;
        age = yrs;
    }


    public Dog(String nm) {
        name = nm;
    }
}
