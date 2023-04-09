package com.jayus.smallSpring.step12.beans;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:05
 * @Version: 1.0
 */
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