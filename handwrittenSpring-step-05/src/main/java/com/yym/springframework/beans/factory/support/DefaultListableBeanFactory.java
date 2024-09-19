package com.yym.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.BeanDefinition;

/**
 * @Description: spring 容器
 *      1. 继承了 AbstractAutowireCapableBeanFactory(实现了bean的实例化)
 *      2. 实现了 AbstractAutowireCapableBeanFactory 父类的 getBeanDefinition方法
 *      3. 实现了 BeanDefinitionRegistry 的 registerBeanDefinition 方法
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 21:12
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("no bean named ' " + beanName + " ' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }


    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}

