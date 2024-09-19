package com.yym.springframework.beans.factory.config;

import com.yym.springframework.beans.PropertyValues;

/**
 * @Description: 存放bean的信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:15
 */
public class BeanDefinition {
    // 此处使用Class, 只存放bean的类信息, 不用将实例化的过程放入
    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = (propertyValues != null) ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public BeanDefinition setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
        return this;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public BeanDefinition setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
        return this;
    }
}
