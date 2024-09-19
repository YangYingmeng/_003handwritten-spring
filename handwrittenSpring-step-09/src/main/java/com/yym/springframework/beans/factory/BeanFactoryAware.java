package com.yym.springframework.beans.factory;

import com.yym.springframework.beans.BeansException;

/**
 * @Description: 容器感知类, 实现该接口 可以感知到所属的BeanFactory
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-02 14:19
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
