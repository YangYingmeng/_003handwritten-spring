package com.yym.springframework.context.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.BeanFactory;
import com.yym.springframework.beans.factory.BeanFactoryAware;
import com.yym.springframework.context.ApplicationEvent;
import com.yym.springframework.context.ApplicationListener;
import com.yym.springframework.util.ClassUtils;

/**
 * @Description: 对事件广播器的公用方法提取
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:18
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        this.applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        this.applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    /**
     * @Description: 获取所有的应用事件监听器
     * @Param: event 事件
     * @Return: allListeners 所有匹配的监听器
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {

        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) allListeners.add(listener);
        }
        return allListeners;
    }


    /**
     * @Description: 监听是否对该事件感兴趣
     * @Param: listener 监听器
     * @Param: event 事件
     * @Return: 是否匹配
     */
    private boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {

        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        // 判断监听器是否由代理生成, 监听的是实际Class对象, 而不是代理类的Class对象
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 获取监听器实现的第一个接口信息, 为了确定泛型的类型
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        // 获取泛型参数类型
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new BeansException("wrong event class name: " + className);
        }

        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
