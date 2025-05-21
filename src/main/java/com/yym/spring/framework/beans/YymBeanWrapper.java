package com.yym.spring.framework.beans;

/**
 * 包装模式
 * 在spring中用来获取JavaBean的属性值, 对 JavaBean 的属性进行封装和操作（读写） 的
 */
public class YymBeanWrapper {

    private Object wrapperInstance;
    private Class<?> wrappedClass;

    public YymBeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
