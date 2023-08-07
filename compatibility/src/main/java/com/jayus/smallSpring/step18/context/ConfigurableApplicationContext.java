package com.jayus.smallSpring.step18.context;

import com.jayus.smallSpring.step18.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
