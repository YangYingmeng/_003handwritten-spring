package com.yym.spring.framework.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 根据请求 URL 查找对应的 Handler（处理器，即 Controller）。
 */
public class YymHandlerMapping {

    // URL 的正则匹配
    private Pattern pattern;
    // 保存映射的方法
    private Method method;
    // Method对应的实例对象
    private Object controller;

    public YymHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.method = method;
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
