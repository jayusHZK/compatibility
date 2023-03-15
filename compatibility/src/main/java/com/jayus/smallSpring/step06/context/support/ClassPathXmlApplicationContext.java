package com.jayus.smallSpring.step06.context.support;

/**
 * @author : h zk
 * @date : 2023/3/15 11:42
 * @description :
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

}
