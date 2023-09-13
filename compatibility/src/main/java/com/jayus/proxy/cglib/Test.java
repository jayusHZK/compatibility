package com.jayus.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {

        Police police = new Police();
        Police p2 = (Police)Enhancer.create(Police.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("工作是");
                Object invoke = method.invoke(police, objects);
                System.out.println("ok,很棒");
                return invoke;
            }
        });
        p2.doWhat();
    }

}
