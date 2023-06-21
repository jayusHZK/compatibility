package com.jayus.smallSpring.step14.beans;

/**
 * @author : h zk
 * @date : 2023/6/21 13:39
 * @description :
 **/
public class PropertyValue {

    private final String name;

    private final String value;

    public PropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
