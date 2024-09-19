package com.yym.springframework.beans.factory.config;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Description: 对beanDefinition的信息进行修改
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 21:56
 */
public interface BeanFactoryPostProcessor {


    /**
     * @Description: 在所有的beanDefinition加载完后, 实例化bean对象之前, 提供修改beanDefinition属性的机制
     *               实例化是指 创建bean并为其属性进行赋值
     * @Param: beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
