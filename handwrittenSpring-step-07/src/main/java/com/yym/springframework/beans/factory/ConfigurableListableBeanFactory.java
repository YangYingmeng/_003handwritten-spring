package com.yym.springframework.beans.factory;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Description: 提供这些接口功能的组合 更加方便的管理bean
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:19
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String name) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
