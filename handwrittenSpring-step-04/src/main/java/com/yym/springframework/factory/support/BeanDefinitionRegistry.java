package com.yym.springframework.factory.support;

import com.yym.springframework.factory.config.BeanDefinition;

/**
 * @Description: 注册beanDefinitionRegistry
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 21:13
 */
public interface BeanDefinitionRegistry {


    /**
     * @Description: 注册beanDefinition
     * @Param: name beanDefinition 名称
     * @Param: beanDefinition bean的定义信息
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
