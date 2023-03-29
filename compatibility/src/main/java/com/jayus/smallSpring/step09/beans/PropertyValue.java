package com.jayus.smallSpring.step09.beans;

/**
 * @author : h zk
 * @date : 2023/3/28 10:34
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

