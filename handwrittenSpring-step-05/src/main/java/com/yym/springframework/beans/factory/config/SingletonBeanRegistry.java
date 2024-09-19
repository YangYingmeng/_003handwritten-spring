package com.yym.springframework.beans.factory.config;

/**
 * @Description: 单例注册接口: 定义一个获取单例对象的接口
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:22
 */
public interface SingletonBeanRegistry {


    /**
     * @Description:
     * @Param: name bean的名称
     * @Return: 返回单例bean
     */
    Object getSingleton(String beanName);
}
