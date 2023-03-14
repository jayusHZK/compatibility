package com.jayus.smallSpring.step06.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/3/14 17:22
 * @description :
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyName.equals(propertyValue.getName())){
                return propertyValue;
            }
        }
        return null;
    }

}
