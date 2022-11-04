package com.jayus.onjava.generic.CompensationErase;

/**
 * @author : h zk
 * @date : 2022/8/10 15:19
 * @description :
 **/
public class XCreator extends GenericWithCreate<X>{
    @Override
    X create() {
        return new X();
    }

    void f(){
        System.out.println(element.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        XCreator xCreator = new XCreator();
        xCreator.f();
    }
}
