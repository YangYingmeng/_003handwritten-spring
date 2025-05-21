package com.yym.spring.framework.aop;

/**
 * 代理顶层接口定义
 */
public interface YymAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
