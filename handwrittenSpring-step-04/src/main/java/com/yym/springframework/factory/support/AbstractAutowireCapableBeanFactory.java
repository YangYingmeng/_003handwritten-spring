package com.yym.springframework.factory.support;

import java.lang.reflect.Constructor;

import com.yym.springframework.BeansException;
import com.yym.springframework.PropertyValue;
import com.yym.springframework.PropertyValues;
import com.yym.springframework.factory.config.BeanDefinition;
import com.yym.springframework.factory.config.BeanReference;

import cn.hutool.core.bean.BeanUtil;

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

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        // 完成bean的实例化 实例化时使用 策略模式 jdk/cglib生成对象
        bean = createBeanInstance(beanDefinition, beanName, args);
        // 实例化后, 给 Bean 填充属性
        applyPropertyValues(beanName, bean, beanDefinition);
        // 注册到单例对象的缓存中
        addSingleton(beanName, bean);
        return bean;
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


    /**
     * @Description: Bean 属性填充
     * @Param: beanName bean名称
     * @Param: bean
     * @Param: beanDefinition bean 定义
     * @Return:
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // 如果A的成员变量 引用了B, 需要将value解析为B对象
                    BeanReference beanReference = (BeanReference) value;
                    // 调用父类方法 获取引用对象    会产生循环依赖问题, 后续解决
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property value:  " + beanName);
        }

    }

}
