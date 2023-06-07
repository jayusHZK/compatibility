package com.jayus.smallSpring.step13.context.support;

import com.jayus.smallSpring.step13.beans.BeansException;

public interface ConfigutableApplicationContext extends ApplicationContextAware{

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
