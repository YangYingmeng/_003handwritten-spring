package com.yym.springframework.springframework.beans.factory.config;

/**
 * @Description: 存放bean的信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:15
 */
public class BeanDefinition {
    // 此处使用Class, 只存放bean的类信息, 不用将实例化的过程放入
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public BeanDefinition setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
        return this;
    }
}
