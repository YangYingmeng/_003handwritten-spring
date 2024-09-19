package com.yym.springframework.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.yym.springframework.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @Description: 单例注册接口的实现类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:24
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    // 只可以被继承了该类的类使用
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
