package com.jayus.jvm.hprof;

public class Hprof {

    public static void main(String[] args) {
        int a = 1;
        while (a == 1){
            //System.out.println(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("123") ));
        /*byte[][] arrs = new byte[20][];
        for (int i = 0; i < 20; i++) {

            arrs[i] = new byte[1024 * 1024];
        }*/
    }

}
