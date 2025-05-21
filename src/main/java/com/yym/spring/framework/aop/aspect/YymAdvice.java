package com.yym.spring.framework.aop.aspect;

import java.lang.reflect.Method;

import lombok.Data;

/**
 * 通知回调
 */
@Data
public class YymAdvice {

    private Object aspect;
    private Method adviceMethod;
    private String throwName;

    public YymAdvice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }
}
