package com.yym.springframework.aop;

/**
 * @Description: 切点表达式的组成, 确定应该匹配哪些类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/10 14:10
 */
public interface ClassFilter {

    /**
     * @Description: 匹配切点表达式的类
     * @Param: clazz
     * @Return: 是否匹配
     */
    boolean matches(Class<?> clazz);
}
