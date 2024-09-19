package com.yym.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 传递类中属性的信息, 一旦实例化后不可修改对应的值
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/26 16:20
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }


    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyName.equals(propertyValue.getName())) {
                return propertyValue;
            }
        }
        return null;
    }
}
