package com.jayus.smallSpring.step11.context.support;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/6 23:11
 * @Version: 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocations) {
       this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}