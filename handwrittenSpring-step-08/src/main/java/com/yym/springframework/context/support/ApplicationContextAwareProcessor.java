package com.yym.springframework.context.support;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.BeanPostProcessor;
import com.yym.springframework.context.ApplicationContext;
import com.yym.springframework.context.ApplicationContextAware;

/**
 * @Description: 如果bean实现了ApplicationContextAware接口, spring会将当前容器的applicationContext对象注入,
 *               如果该bean同时实现了beanPostProcessor接口, 并在前置方法使用applicationContext会导致null指针异常
 *               所以需要在beanPostProcessor中也保存上下文
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-02 14:27
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware)bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
