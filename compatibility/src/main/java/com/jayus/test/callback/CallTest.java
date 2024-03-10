package com.jayus.test.callback;

public class CallTest {

    public void test(String a,String b,CallAble callAble){
        callAble.call();
    }

    public static void main(String[] args) {
        new CallTest().test("a", "b", new CallAble() {
            @Override
            public void call() {
                System.out.println("call");
            }
        });
    }


}
