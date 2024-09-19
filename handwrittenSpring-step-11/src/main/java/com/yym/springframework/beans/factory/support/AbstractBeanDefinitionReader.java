package com.yym.springframework.beans.factory.support;

import com.yym.springframework.core.io.DefaultResourceLoader;
import com.yym.springframework.core.io.ResourceLoader;

/**
 * @Description: BeanDefinitionReader 的抽象类, 用于实现BeanDefinitionReader中的工具方法
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/28 9:34
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    // 该属性在类实例化后不需要更改
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(beanDefinitionRegistry, new DefaultResourceLoader());
    }

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }


    /**
     * @Description: 返回构造器提供的BeanDefinitionRegistry. 并且不允许被更改
     */
    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
