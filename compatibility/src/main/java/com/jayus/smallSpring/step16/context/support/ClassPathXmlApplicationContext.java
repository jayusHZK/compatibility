package com.jayus.smallSpring.step16.context.support;

/**
 * @author : h zk
 * @date : 2023/7/17 16:33
 * @description :
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocation() {
        return configLocations;
    }

}
