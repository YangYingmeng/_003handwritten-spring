package com.yym.springframework.aop.framework.autoproxy;

import java.util.Collection;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.yym.springframework.aop.AdvisedSupport;
import com.yym.springframework.aop.Advisor;
import com.yym.springframework.aop.ClassFilter;
import com.yym.springframework.aop.Pointcut;
import com.yym.springframework.aop.TargetSource;
import com.yym.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.yym.springframework.aop.framework.ProxyFactory;
import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.BeanFactory;
import com.yym.springframework.beans.factory.BeanFactoryAware;
import com.yym.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.yym.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Description: 融入Bean生命周期的自动代理创建者
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 10:38
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }

        return null;
    }


    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }


    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass);
    }}
