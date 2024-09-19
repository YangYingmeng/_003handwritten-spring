package com.yym.springframework.beans.factory;

import com.yym.springframework.beans.BeansException;

/**
 * @Description:
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:30
 */
public interface BeanFactory {

    /**
     * @Description: 根据bean的名称获取ben 只能用于无参构造
     * @Param: name bean的名称
     * @Return: 返回对应的bean
     */
    public Object getBean(String beanName) throws BeansException;

    /**
     * @Description: 根据bean的名称, 参数获取ben 实现有参构造
     * @Param: name bean的名称
     * @Param: args bean的参数
     * @Return: 返回对应的bean
     */
    public Object getBean(String beanName, Object ... args) throws BeansException;
}
