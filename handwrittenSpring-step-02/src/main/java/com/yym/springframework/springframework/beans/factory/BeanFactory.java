package com.yym.springframework.springframework.beans.factory;

import com.yym.springframework.springframework.beans.BeansException;

/**
 * @Description:
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:30
 */
public interface BeanFactory {

    /**
     * @Description: 根据bean的名称获取ben
     * @Param: name bean的名称
     * @Return: 返回对应的bean
     */
    public Object getBean(String beanName) throws BeansException;
}
