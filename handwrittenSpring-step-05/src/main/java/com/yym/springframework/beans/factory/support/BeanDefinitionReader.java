package com.yym.springframework.beans.factory.support;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.core.io.Resource;
import com.yym.springframework.core.io.ResourceLoader;

/**
 * @Description: 定义简单的读取beanDefinition的接口
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 16:44
 */
public interface BeanDefinitionReader {


    /**
     * @Description: 该方法提供给后面loadBeanDefinitions()的工具, 包装到抽象类中 避免污染具体的接口实现方法
     * @Return: BeanDefinitionRegistry
     */
    BeanDefinitionRegistry getBeanDefinitionRegistry();

    /**
     * @Description: 该方法提供给后面loadBeanDefinitions()的工具, 包装到抽象类中 避免污染具体的接口实现方法
     * @Return: ResourceLoader
     */
    ResourceLoader getResourceLoader();


    /**
     * @Description: 加载beanDefinitions
     * @Param: resource (classPath url file)
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * @Description: 加载beanDefinitions
     * @Param: resource (classPath url file)
     */
    void loadBeanDefinitions(Resource ... resources) throws BeansException;

    /**
     * @Description: 加载beanDefinitions
     * @Param: resource (classPath url file)
     */
    void loadBeanDefinitions(String location) throws BeansException;
}
