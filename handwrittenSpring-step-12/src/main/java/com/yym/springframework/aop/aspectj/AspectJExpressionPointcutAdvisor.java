package com.yym.springframework.aop.aspectj;

import org.aopalliance.aop.Advice;

import com.yym.springframework.aop.Pointcut;
import com.yym.springframework.aop.PointcutAdvisor;

/**
 * @Description: 实现 PointcutAdvisor 接口, 把切面pointcut 拦截方法advice 和具体的拦截表达式包装在一起
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/11 16:37
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
