package com.yym.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @Description: 访问者 承担了Pointcut和Advice的组合; Pointcut用户获取joinPoint Advice决定joinPoint执行什么操作
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/11 16:31
 */
public interface Advisor {

    Advice getAdvice();
}
