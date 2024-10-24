package com.jayus.rpc_study.step03.config.spring;

import com.jayus.rpc_study.step03.config.spring.bean.ConsumerBean;
import com.jayus.rpc_study.step03.config.spring.bean.ProviderBean;
import com.jayus.rpc_study.step03.config.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @ClassName MyNamespaceHandler
 * @Description:
 * @date: 2024/10/22 23:40
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("consumer",new MyBeanDefinitionParser(ConsumerBean.class));
        registerBeanDefinitionParser("provider",new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("server",new MyBeanDefinitionParser(ServerBean.class));
    }
}
