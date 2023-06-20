package com.jayus.smallSpring.step13.context;

import com.jayus.smallSpring.step13.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
