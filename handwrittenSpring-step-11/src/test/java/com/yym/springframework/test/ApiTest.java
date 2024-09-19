package com.yym.springframework.test;


import java.lang.reflect.Method;

import org.junit.Test;

import com.yym.springframework.aop.AdvisedSupport;
import com.yym.springframework.aop.TargetSource;
import com.yym.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.yym.springframework.aop.framework.Cglib2AopProxy;
import com.yym.springframework.aop.framework.JdkDynamicAopProxy;
import com.yym.springframework.test.bean.IUserService;
import com.yym.springframework.test.bean.UserService;
import com.yym.springframework.test.bean.UserServiceInterceptor;

/**
 * @Description: Test
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/9 17:31
 */
public class ApiTest {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.yym.springframework.test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.yym.springframework.test.bean.IUserService.*(..))"));

        // 代理对象(jdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用 代理类在调用接口的具体方法时, 会调用 InvocationHandler 的 invoke()方法
        // 1. jdkProxy 实现了 InvocationHandler接口, 调用invoke方法;
        // 2. 获取MethodInterceptor, 调用MethodInterceptor中的invoke方法
        // 3. 用户重写 MethodInterceptor中的invoke()
        System.out.println("测试结果: " + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果: " + proxy_cglib.register("花花"));
    }
}
