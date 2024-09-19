package com.yym.springframework.beans.factory.support;

import java.lang.reflect.Constructor;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.BeanDefinition;

/**
 * @Description: 实例化策略接口
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/25 13:56
 */
public interface InstantiationStrategy {


    /**
     * @Description: 实例化
     * @Param: beanDefinition bean的定义信息
     * @Param: name bean的名称
     * @Param: constructor bean的构造器
     * @Param: args bean的成员属性
     * @Return:
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object ... args) throws BeansException;
}
