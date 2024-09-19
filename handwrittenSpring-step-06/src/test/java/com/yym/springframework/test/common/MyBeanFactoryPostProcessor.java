package com.yym.springframework.test.common;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.PropertyValue;
import com.yym.springframework.beans.PropertyValues;
import com.yym.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Description: 自定义beanFactoryPostProcessor
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 11:24
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
         propertyValues.addPropertyValue(new PropertyValue("company", "改为: 字节跳动"));
    }
}
