package com.yym.springframework.beans;

/**
 * @Description: 传递类中属性的信息, 一旦实例化后不可修改对应的值
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/26 16:11
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
