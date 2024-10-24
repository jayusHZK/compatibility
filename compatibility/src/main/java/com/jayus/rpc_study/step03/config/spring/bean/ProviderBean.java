package com.jayus.rpc_study.step03.config.spring.bean;

import com.alibaba.fastjson.JSON;
import com.jayus.rpc_study.step03.config.ProviderConfig;
import com.jayus.rpc_study.step03.domain.LocalServerInfo;
import com.jayus.rpc_study.step03.domain.RpcProviderConfig;
import com.jayus.rpc_study.step03.register.RedisRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName ProviderBean
 * @Description:
 * @date: 2024/10/22 23:10
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ProviderBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RpcProviderConfig rpcProviderConfig = new RpcProviderConfig();
        rpcProviderConfig.setNozzle(nozzle);
        rpcProviderConfig.setRef(ref);
        rpcProviderConfig.setAlias(alias);
        rpcProviderConfig.setHost(LocalServerInfo.LOCAL_HOST);
        rpcProviderConfig.setPort(LocalServerInfo.LOCAL_PORT);
        Long count = RedisRegistryCenter.registryProvider(nozzle, alias, JSON.toJSONString(rpcProviderConfig));
        logger.info("注册生产者：{} {} {}", nozzle, alias, count);
    }
}
