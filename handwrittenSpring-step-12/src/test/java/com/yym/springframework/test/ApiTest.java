package com.yym.springframework.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import com.yym.springframework.aop.AdvisedSupport;
import com.yym.springframework.aop.ClassFilter;
import com.yym.springframework.aop.TargetSource;
import com.yym.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.yym.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.yym.springframework.aop.framework.ProxyFactory;
import com.yym.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.yym.springframework.context.support.ClassPathXmlApplicationContext;
import com.yym.springframework.test.bean.IUserService;
import com.yym.springframework.test.bean.UserService;
import com.yym.springframework.test.bean.UserServiceBeforeAdvice;
import com.yym.springframework.test.bean.UserServiceInterceptor;

/**
 * @Description: Test
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 13:14
 */
public class ApiTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装目标对象
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.yym.springframework.test.bean.IUserService.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println("测试结果: " + proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor adviceInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(adviceInterceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果: " + proxy.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        IUserService target = new UserService();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.yym.springframework.test.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(target.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setTargetSource(new TargetSource(target));
            // MethodInterceptor 也是一种通知 时advice的子接口
            advisedSupport.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);

            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println(proxy.queryUserInfo());
        }
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classPath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
