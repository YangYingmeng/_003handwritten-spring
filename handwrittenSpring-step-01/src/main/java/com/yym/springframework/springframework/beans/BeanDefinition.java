package com.yym.springframework.springframework.beans;

/**
 * @Description: 用于定义 Bean 实例化信息，现在的实现是以一个 Object 存放对象
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 17:53
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return this.bean;
    }
}
