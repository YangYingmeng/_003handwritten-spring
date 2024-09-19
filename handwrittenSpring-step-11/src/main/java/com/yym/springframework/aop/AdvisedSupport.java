package com.yym.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Description: 把代理 拦截 匹配的各项属性包装到一个类中, 方便在Proxy实现类中使用(业务类包装入参)
 *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/10 16:12
 */
public class AdvisedSupport {

    // 被代理对象
    private TargetSource targetSource;
    // 方法拦截器
    private MethodInterceptor methodInterceptor;
    // 方法匹配器(检查目标方法是否符合通知条件)
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public AdvisedSupport setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
        return this;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public AdvisedSupport setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
        return this;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public AdvisedSupport setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
        return this;
    }
}
