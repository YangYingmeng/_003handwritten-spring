package com.yym.springframework.beans.factory.config;

import com.yym.springframework.beans.BeansException;

/**
 * @Description: BeanPostProcessor 的子接口, 对外提供口子
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 10:44
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {


    /**
     * @Description: 在bean对象实例化之前 执行此方法
     * @Param: beanClass
     * @Param: beanName
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
