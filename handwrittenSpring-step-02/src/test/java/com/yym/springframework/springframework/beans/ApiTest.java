package com.yym.springframework.springframework.beans;

import org.junit.Test;

import com.yym.springframework.springframework.beans.bean.UserService;
import com.yym.springframework.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 21:28
 */
public class ApiTest {

    @Test
    public void test_beanFactory() {
        // 1. 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3. 第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4. 第二次获取bean
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();
    }

    /**
     * @Description:
     *      先从单例池中获取bean,
     *          1. 获取到直接返回
     *          2. 单例池获取不到, 从beanDefinitionMap中获取beanDefinition, 通过beanDefinition创建bean, add进单例池
     */
    @Test
    public void test1() {
        // 1. 初始化容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 初始化beanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        // 3. 注册beanDefinition
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 4. 首次获取bean, create
        beanFactory.getBean("userService");
        // 5. 第二次获取bean, 直接get
        beanFactory.getBean("userService");

    }
}
