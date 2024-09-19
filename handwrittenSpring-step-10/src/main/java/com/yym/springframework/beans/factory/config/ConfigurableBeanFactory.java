package com.yym.springframework.beans.factory.config;

import com.yym.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @Description: 用于在运行时创建和管理bean对象, 使开发人员可以在运行时动态的注册 修改 删除beanDefinition
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:08
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
