package com.jayus.rpc_study.step03.config.spring.bean;

import com.jayus.rpc_study.step03.config.ServerConfig;
import com.jayus.rpc_study.step03.domain.LocalServerInfo;
import com.jayus.rpc_study.step03.network.server.ServerSocket;
import com.jayus.rpc_study.step03.register.RedisRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName ServerBean
 * @Description:
 * @date: 2024/10/22 23:13
 */
public class ServerBean extends ServerConfig implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ServerBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("启动注册中心");
        RedisRegistryCenter.init(host,port);
        logger.info("启动注册中心完成 {} {}",host,port);

        logger.info("初始化生产端服务...");
        ServerSocket serverSocket = new ServerSocket(applicationContext);
        Thread thread = new Thread(serverSocket);
        thread.start();
        while (!serverSocket.isActiveSocketServer()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
        logger.info("初始化生产端服务完成 {} {}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);
    }
}
