package com.jayus.jvm.hprof;

public class Hprof {

    int a;

    public static void main(String[] args) throws Exception {
        int i = 1;
        while(true){
            Hprof hprof = new Hprof();
            hprof.a=i++;
            // 模拟方法区溢出
            Hprof.class.getMethod("say",null).invoke(hprof,null);
        }
        // 模拟堆溢出
        /*List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]);
        }*/
    }

    public void say(){
        System.out.println(a);
    }

}
