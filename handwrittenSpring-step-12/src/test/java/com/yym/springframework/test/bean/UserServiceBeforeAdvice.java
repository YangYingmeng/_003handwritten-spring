package com.yym.springframework.test.bean;

import java.lang.reflect.Method;

import com.yym.springframework.aop.MethodBeforeAdvice;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 13:19
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法: " + method.getName());
    }
}
