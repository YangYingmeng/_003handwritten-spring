package com.yym.springframework.springframework.beans.factory.support;

import com.yym.springframework.springframework.beans.BeansException;
import com.yym.springframework.springframework.beans.factory.BeanFactory;
import com.yym.springframework.springframework.beans.factory.config.BeanDefinition;

/**
 * @Description: 抽象类定义模板方法 模板设计
 *      1. 继承了 DefaultSingletonBeanRegistry, 可以获取单实例的bean
 *      2. 此处getBean只是定义了获取bean的调用过程以及提供抽象方法, 并未对其实现
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:29
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
