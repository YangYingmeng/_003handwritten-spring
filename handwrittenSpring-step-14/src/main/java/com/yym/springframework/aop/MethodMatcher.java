package com.yym.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Description: Part of a {@link Pointcut}: 方法匹配, 匹配表达式中的方法
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/10 13:57
 */
public interface MethodMatcher {


    /**
     * @Description: 匹配切点表达式中的方法
     * @Param: method 方法
     * @Param: targetClass 方法
     * @Return: boolean
     */
    boolean matches(Method method, Class<?> targetClass);
}
