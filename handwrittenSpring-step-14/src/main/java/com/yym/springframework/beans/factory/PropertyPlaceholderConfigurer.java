package com.yym.springframework.beans.factory;

import java.io.IOException;
import java.util.Properties;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.PropertyValue;
import com.yym.springframework.beans.PropertyValues;
import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yym.springframework.core.io.DefaultResourceLoader;
import com.yym.springframework.core.io.Resource;

/**
 * @Description: 处理占位符配置
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 11:01
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();

                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuffer buffer = new StringBuffer(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propValue = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx+1, propValue);
                        propertyValues.addPropertyValue(new PropertyValue(propKey, buffer.toString()));
                    }

                }

            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
