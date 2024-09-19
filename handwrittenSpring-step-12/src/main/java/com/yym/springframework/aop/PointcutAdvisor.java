package com.yym.springframework.aop;

/**
 * @Description: 用于获取JoinPoint
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/11 16:33
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
