package com.yym.springframework.context.support;

import com.yym.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yym.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Description: 上下文中对配置信息的加载
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 23:49
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
