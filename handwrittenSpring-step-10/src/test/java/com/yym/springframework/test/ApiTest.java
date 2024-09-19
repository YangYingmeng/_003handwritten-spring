package com.yym.springframework.test;

import org.junit.Test;

import com.yym.springframework.context.support.ClassPathXmlApplicationContext;
import com.yym.springframework.test.event.CustomEvent;

/**
 * @Description: Test
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 17:09
 */
public class ApiTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classPath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 123456L, "test customer event"));

        applicationContext.registerShutdownHook();
    }
}
