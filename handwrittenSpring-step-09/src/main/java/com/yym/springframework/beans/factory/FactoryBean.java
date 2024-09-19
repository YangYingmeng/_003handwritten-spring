package com.yym.springframework.beans.factory;

/**
 * @Description: bean工厂, 根据需要动态的创建其它bean
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-07 11:59
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
