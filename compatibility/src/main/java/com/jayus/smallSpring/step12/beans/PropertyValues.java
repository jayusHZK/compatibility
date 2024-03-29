package com.jayus.smallSpring.step12.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 18:05
 * @Version: 1.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }

}