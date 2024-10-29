package com.jayus.jvm.priorityLoad;

import java.lang.instrument.Instrumentation;

/*
jvm 在加载时就执行的方法 拦截用户类的加载
使用参数 -javaagent:jar包路径 启动要代理的方法  java -javaagent:xxx.jar=args main
打包方式也不同 运行可参考 jvm-load 项目
需要添加 MANIFEST.MF 文件 或者用 maven manifestEntries 标签
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
