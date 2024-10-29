package com.jayus.attachAgent;

import java.lang.instrument.Instrumentation;

/**
 * @ClassName AttachAgent
 * @Description:
 * @date: 2024/10/29 23:11
 */
public class AttachAgent {

    /**
     * 动态 attach 方式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("com.jayus.monitor.agent.MonitorAgent.agentmain：" + agentArgs);
    }

}
