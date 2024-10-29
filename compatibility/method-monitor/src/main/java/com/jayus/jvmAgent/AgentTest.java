package com.jayus.jvmAgent;

import java.lang.instrument.Instrumentation;

/*
jvm 在加载时就执行的方法
 */
/*
https://blog.csdn.net/qq_40131760/article/details/124657060
 */
public class AgentTest {

    /*
    jvm 优先加载带 Instrumentation 入参的方法
     */
    public static void premain(String args, Instrumentation in) throws Exception{
        System.out.println("com.jayus.jvm.priority.test.premain()!!!");
        System.out.println(args);
    }

}
