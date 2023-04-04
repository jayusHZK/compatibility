package com.jayus.onjava.function.CtorReference;

/**
 * @author : h zk
 * @date : 2022/7/19 11:19
 * @description :
 **/
public class CtorRefence {

    public static void main(String[] args) {
        MakeNoArgs mna = Dog::new;
        Make1Arg m1a = Dog::new;
        Make2Args m2a = Dog::new;

        Dog make = mna.make();
        Dog qiumin = m1a.make("qiumin");
        Dog qiumin1 = m2a.make("qiumin", 2);


    }

}

