package com.yym.springframework.beans.factory.config;

import com.yym.springframework.beans.BeansException;

/**
 * @Description: 在bean对象实例化之后, 初始化前后, 执行该方法, 修改bean的信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:35
 */
public interface BeanPostProcessor {


    /**
     * @Description: 在bean实例化之后 初始化之前执行该方法
     * @Param: bean
     * @Param: beanName
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * @Description: 在bean初始化之后执行该方法
     * @Param: bean
     * @Param: beanName
     */
    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
