package com.yym.springframework.test.event;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yym.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-09-20 15:57
 */
public class Test implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
