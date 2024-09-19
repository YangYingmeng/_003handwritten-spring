package com.yym.springframework.context.support;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yym.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Description: 获取bean工厂和加载资源
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 23:38
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 由其它抽象类实现 完成对xml文件中的bean对象定义和注册 包括实现 beanFactoryPostProcessor 和 beanPostProcessor的处理
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
