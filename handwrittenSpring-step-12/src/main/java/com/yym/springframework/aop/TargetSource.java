package com.yym.springframework.aop;

/**
 * @Description: 被代理的目标对象
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/10 16:16
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }


    /**
     * @Description: 获取目标对象的Class对象(由于JDK动态代理只能代理实现了接口的目标对象, CGLIB可以代理任何类型的目标对象 所以返回接口)
     */
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }


    /**
     * @Description: 获取目标对象的实例
     */
    public Object getTarget() {
        return this.target;
    }
}
