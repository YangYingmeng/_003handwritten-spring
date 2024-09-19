package com.yym.springframework.beans.factory.config;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.BeanFactory;

/**
 * @Description: 预留扩展接口 实现自动装配功能
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:07
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * @Description: 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param: existingBean
     * @param: beanName
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * @Description: 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     * @param: existingBean
     * @param: beanName
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
