package com.jayus.monitor.agent;

import com.jayus.monitor.transformer.MonitorTransformer;
import javassist.ClassPool;

import java.lang.instrument.Instrumentation;

/**
 * @ClassName MonitorAgent
 * @Description: 将本项目打包后 使用命令
 * java -javaagent:xx.jar -jar yyy.jar 可启动对对应 yyy.jar 的检测
 * @date: 2024/10/29 19:32
 */
public class MonitorAgent {

    /*
    jvm 参数形式启动，运行此方法 java -javaagent:xx.jar
     */
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("in premain");
        ClassPool pool = ClassPool.getDefault();
        String config = args;

        MonitorTransformer transformer = new MonitorTransformer(config, pool);
        instrumentation.addTransformer(transformer);
    }


}
