package com.jayus.smallSpring.step16.beans;

/**
 * @author : h zk
 * @date : 2023/7/14 10:07
 * @description :
 **/
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
