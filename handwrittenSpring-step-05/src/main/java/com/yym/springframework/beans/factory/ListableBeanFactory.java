package com.yym.springframework.beans.factory;

import java.util.Map;

import com.yym.springframework.beans.BeansException;

/**
 * @Description: 获取spring容器中所有 bean的名称 和 类型
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:14
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param: type
     * @param: <T>
     * @throws: BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
