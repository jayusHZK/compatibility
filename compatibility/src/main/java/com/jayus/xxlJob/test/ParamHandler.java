package com.jayus.xxlJob.test;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : h zk
 * @date : 2023/8/3 17:33
 * @description :
 **/
@Slf4j
@Component
public class ParamHandler {

    @XxlJob("paramHandler")
    public void exce() {
        String jobParam = XxlJobHelper.getJobParam();
        log.info(jobParam);
    }
    
}
