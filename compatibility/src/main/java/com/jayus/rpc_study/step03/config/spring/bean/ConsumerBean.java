package com.jayus.rpc_study.step03.config.spring.bean;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.jayus.rpc_study.step03.config.ConsumerConfig;
import com.jayus.rpc_study.step03.domain.RpcProviderConfig;
import com.jayus.rpc_study.step03.network.client.ClientSocket;
import com.jayus.rpc_study.step03.network.msg.Request;
import com.jayus.rpc_study.step03.reflect.JDKProxy;
import com.jayus.rpc_study.step03.register.RedisRegistryCenter;
import com.jayus.rpc_study.step03.util.ClassLoaderUtils;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName ConsumerBean
 * @Description:
 * @date: 2024/10/22 20:49
 */
public class ConsumerBean<T> extends ConsumerConfig<T> implements FactoryBean {

    private ChannelFuture channelFuture;

    private RpcProviderConfig rpcProviderConfig;

    @Override
    public Object getObject() throws Exception {
        if (null == rpcProviderConfig) {
            String infoStr = RedisRegistryCenter.obtainProvider(nozzle, alias);
            rpcProviderConfig = JSON.parseObject(infoStr,RpcProviderConfig.class);
        }
        Assert.isTrue(null != rpcProviderConfig);

        if (null == channelFuture) {
            ClientSocket clientSocket = new ClientSocket(rpcProviderConfig.getHost(), rpcProviderConfig.getPort());
            new Thread(clientSocket).start();
            for (int i = 0; i < 100; i++) {
                if (null != channelFuture) break;
                Thread.sleep(500);
                channelFuture =clientSocket.getFuture();
            }
        }

        Assert.isTrue(null != channelFuture);
        Request request = new Request();
        request.setChannel(channelFuture.channel());
        request.setNozzle(nozzle);
        request.setRef(rpcProviderConfig.getRef());
        request.setAlias(alias);
        return JDKProxy.getProxy(ClassLoaderUtils.forName(nozzle),request);

    }

    @Override
    public Class<?> getObjectType() {
        try {
            return ClassLoaderUtils.forName(nozzle);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
