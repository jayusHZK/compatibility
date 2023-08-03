package com.jayus.xxlJob.test;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author : h zk
 * @date : 2023/8/3 17:20
 * @description :
 **/
@Component
public class ExecHandler {

    @XxlJob("execHandler")
    public void exce() {
        System.out.println("hello world!!");
    }

}
