package com.yym.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.DisposableBean;
import com.yym.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @Description: 单例注册接口的实现类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:24
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();


    private Map<String, Object> singletonObjects = new HashMap<>();

    private Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    // 只可以被继承了该类的类使用
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBeans(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        // 采用倒序遍历, 先销毁对象依赖的对象 A->B->C 先销毁C
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception ex) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", ex);
            }
        }
    }
}
