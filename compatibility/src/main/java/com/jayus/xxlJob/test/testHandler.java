package com.jayus.xxlJob.test;

import com.xxl.job.core.handler.IJobHandler;

/**
 * @author : h zk
 * @date : 2023/8/3 10:38
 * @description :
 **/
public class testHandler extends IJobHandler {

    @Override
    public void execute() throws Exception {
        System.out.println("hello world!!");
    }




}
