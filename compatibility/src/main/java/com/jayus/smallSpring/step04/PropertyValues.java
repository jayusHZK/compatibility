package com.jayus.smallSpring.step04;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: h zk
 * @Description: bean 属性集合
 * @Date: 2023/3/12 13:39
 * @Version: 1.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValues.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : this.propertyValues) {
            if (pv.getName().equalsIgnoreCase(propertyName)){
                return pv;
            }
        }
        return null;
    }

}