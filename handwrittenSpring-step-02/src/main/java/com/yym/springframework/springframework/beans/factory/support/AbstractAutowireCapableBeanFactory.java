package com.yym.springframework.springframework.beans.factory.support;

import com.yym.springframework.springframework.beans.BeansException;
import com.yym.springframework.springframework.beans.factory.config.BeanDefinition;

/**
 * @Description: 实现bean的实例化, 并注册
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:59
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        // 完成bean的实例化
        try {
            // 实例化使用 Class.newInstance()使用默认构造函数(无参构造) 有参构造会有问题 step3进行改造
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 注册到单例对象的缓存中
        addSingleton(beanName, bean);
        return bean;
    }

}
