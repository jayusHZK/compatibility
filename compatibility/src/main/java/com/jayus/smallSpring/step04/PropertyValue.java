package com.jayus.smallSpring.step04;

/**
 * @Author: h zk
 * @Description: bean 属性信息
 * @Date: 2023/3/12 13:36
 * @Version: 1.0
 */
public class PropertyValue {

    // bean名称
    private final String name;

    // bean实例
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