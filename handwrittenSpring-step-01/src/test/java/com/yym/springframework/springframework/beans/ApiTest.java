package com.yym.springframework.springframework.beans;

import org.junit.Test;

import com.yym.springframework.springframework.beans.bean.UserService;

/**
 * @Description: 测试案例
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 18:21
 */
public class ApiTest {

    @Test
    public void test_beanFactory() {

        // 1. 初始化beanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2. 加载beanDefinition
        UserService userService = new UserService();
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(userService));

        // 3. 获取bean, 调用方法
        UserService bean = (UserService) beanFactory.getBean("userService");
        bean.queryUserInfo();
    }
}
