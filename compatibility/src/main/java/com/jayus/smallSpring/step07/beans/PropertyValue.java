package com.jayus.smallSpring.step07.beans;

/**
 * @author : h zk
 * @date : 2023/3/17 13:59
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
