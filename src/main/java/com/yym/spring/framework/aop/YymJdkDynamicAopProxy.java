package com.yym.spring.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.yym.spring.framework.aop.aspect.YymAdvice;
import com.yym.spring.framework.aop.support.YymAdvisedSupport;


/**
 * YymJdkDynamicAopProxy 是一个基于 JDK 动态代理的 AOP 实现类。
 * 它实现了 InvocationHandler 接口，用于在代理方法执行前后织入横切逻辑（Advice）。
 */
public class YymJdkDynamicAopProxy implements InvocationHandler {

    // 封装了切面配置、目标对象等 AOP 所需元数据
    private YymAdvisedSupport config;

    // 构造方法，传入配置
    public YymJdkDynamicAopProxy(YymAdvisedSupport config) {
        this.config = config;
    }

    /**
     * JDK 动态代理的核心方法：拦截代理对象的方法调用
     *
     * @param proxy  代理对象
     * @param method 被代理的方法
     * @param args   方法参数
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取当前方法对应的增强配置（before、after、afterThrow）
        Map<String, YymAdvice> advices = config.getAdvices(method, null);

        Object returnValue;
        try {
            // 执行前置通知（如果有）
            invokeAdivce(advices.get("before"));

            // 反射调用目标类的方法
            returnValue = method.invoke(this.config.getTarget(), args);

            // 执行后置通知（如果有）
            invokeAdivce(advices.get("after"));
        } catch (Exception e) {
            // 异常通知（如果配置了）
            invokeAdivce(advices.get("afterThrow"));
            throw e; // 继续抛出异常
        }

        return returnValue;
    }

    /**
     * 执行增强方法（Advice）
     *
     * @param advice 增强封装对象，包含切面类与方法
     */
    private void invokeAdivce(YymAdvice advice) {
        if (advice == null) {
            return; // 没有配置则跳过
        }
        try {
            // 执行切面类中配置的方法
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // 打印异常堆栈
        }
    }

    /**
     * 创建并返回代理对象
     *
     * @return 基于 JDK 的代理对象
     */
    public Object getProxy() {
        // 用当前类作为 InvocationHandler，生成代理对象
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                this.config.getTargetClass().getInterfaces(), // 只能代理接口
                this
        );
    }
}

