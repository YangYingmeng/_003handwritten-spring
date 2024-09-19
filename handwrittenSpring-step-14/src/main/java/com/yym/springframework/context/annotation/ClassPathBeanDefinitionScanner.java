package com.yym.springframework.context.annotation;

import java.util.Set;

import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.yym.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 11:32
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doScan(String ... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponent(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析bean的作用域
                String beanScope = resolveScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }

                beanDefinitionRegistry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }


    /**
     * @Description: 根据scope注解解析bean的作用域
     */
    private String resolveScope(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Scope scope = (Scope) beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }


    /**
     * @Description: 如果未配置类名, 则用类名的首字母小写作为bean的名称
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getBeanClass();
        Component component = (Component) clazz.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value =StrUtil.lowerFirst(clazz.getSimpleName());
        }
        return value;
    }

}
