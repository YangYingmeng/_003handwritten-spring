package com.yym.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Description: 在目标方法执行前执行逻辑的通知类型
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/11 16:24
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     *
     * @param method method being invoked
     * @param args   arguments to the method
     * @param target target of the method invocation. May be <code>null</code>.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
