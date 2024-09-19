package com.yym.springframework.aop;

/**
 * @Description: 切入点接口, ClassFilter 和 MethodMatcher: 切点表达式的组成部分
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/10 13:54
 */
public interface Pointcut {


    /**
     * @Description: 应该匹配哪些类
     * @Return: ClassFilter
     */
    ClassFilter getClassFilter();

    /**
     * @Description: 应该匹配哪些方法
     * @Return: the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
