package com.jayus.smallSpring.step17.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/7/27 10:22
 * @description :
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(pv.getName())){
                this.propertyValueList.set(i,pv);
                return;
            }
        }
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
