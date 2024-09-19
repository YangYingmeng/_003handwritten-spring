package com.yym.springframework.beans.factory.support;

import java.lang.reflect.Method;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.DisposableBean;
import com.yym.springframework.beans.factory.config.BeanDefinition;

import cn.hutool.core.util.StrUtil;

/**
 * @Description: 销毁方法适配器(销毁的方式有多种)
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 17:01
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean)bean).destroy();
        }

        // 2. 注解配置 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }
}
