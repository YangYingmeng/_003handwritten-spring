package com.yym.springframework.context.annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.stereotype.Component;

import cn.hutool.core.util.ClassUtil;

/**
 * @Description: 处理对象扫描装配, 获取候选类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 11:26
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponent(String basePackage) {
        LinkedHashSet<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);

        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
