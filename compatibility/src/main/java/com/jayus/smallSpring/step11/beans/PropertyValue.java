package com.jayus.smallSpring.step11.beans;

/**
 * @author : h zk
 * @date : 2023/4/4 18:09
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
