package com.jayus.smallSpring.step15.beans;

/**
 * @author : h zk
 * @date : 2023/7/5 18:16
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
