package com.yym.springframework.factory.support;

import java.lang.reflect.Constructor;

import com.yym.springframework.BeansException;
import com.yym.springframework.factory.config.BeanDefinition;

/**
 * @Description: 实现bean的实例化, 并注册
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:59
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    // 为外部类提供方法
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor<?> constructor : declaredConstructors) {
            if (null != args && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        // 完成bean的实例化 实例化时使用 策略模式 jdk/cglib生成对象
        bean = createBeanInstance(beanDefinition, beanName, args);
        // 注册到单例对象的缓存中
        addSingleton(beanName, bean);
        return bean;
    }

}
