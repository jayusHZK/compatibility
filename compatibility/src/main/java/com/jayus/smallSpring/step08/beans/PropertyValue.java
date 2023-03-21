package com.jayus.smallSpring.step08.beans;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/21 22:53
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