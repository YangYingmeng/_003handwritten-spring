package com.yym.springframework.beans.factory.support;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.BeanDefinition;

/**
 * @Description: 注册beanDefinitionRegistry
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 21:13
 */
public interface BeanDefinitionRegistry {


    /**
     * @Description: 向注册表中注册 BeanDefinition
     * @Param: name beanDefinition 名称
     * @Param: beanDefinition bean的定义信息
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);


    /**
     * @Description: 使用Bean名称查询BeanDefinition
     * @Param: beanName
     * @Return: BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    /**
     * @Description: 判断是否包含指定名称的BeanDefinition
     * @Param: beanName
     * @Return:
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * @Description: 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
