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

    // bean需要填充的属性
    private PropertyValues propertyValues;

    // 初始化方法名称
    private String initMethodName;

    // 销毁方法名称
    private String destroyMethodName;

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

    public String getInitMethodName() {
        return initMethodName;
    }

    public BeanDefinition setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
        return this;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public BeanDefinition setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
        return this;
    }
}
