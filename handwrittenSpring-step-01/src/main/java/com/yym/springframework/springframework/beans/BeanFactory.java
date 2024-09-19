package com.yym.springframework.springframework.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 代表了 Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 18:04
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
