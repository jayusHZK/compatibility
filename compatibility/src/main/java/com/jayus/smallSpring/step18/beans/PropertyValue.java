package com.jayus.smallSpring.step18.beans;

/**
 * @author : h zk
 * @date : 2023/8/2 17:12
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
