package com.yym.springframework.test;

import org.junit.Test;

import com.yym.springframework.context.support.ClassPathXmlApplicationContext;
import com.yym.springframework.test.bean.UserService;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 22:57
 */
public class ApiTest {

    @Test
    public void test_xml() {
        // 1. 初始化 beanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classPath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println("测试结果: " + userService.queryUserInfo());
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }

}
