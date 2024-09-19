package com.yym.springframework.test.common;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.BeanPostProcessor;
import com.yym.springframework.test.bean.UserService;

/**
 * @Description: 自定义beanPostProcessor
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 11:28
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("北京");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
