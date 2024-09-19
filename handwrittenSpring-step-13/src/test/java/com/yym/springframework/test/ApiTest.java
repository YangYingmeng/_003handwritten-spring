package com.yym.springframework.test;

import org.junit.Test;

import com.yym.springframework.context.support.ClassPathXmlApplicationContext;
import com.yym.springframework.test.bean.UserService;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 14:44
 */
public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classPath:spring-scan.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classPath:spring-property.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果: " + userService);
    }

}
