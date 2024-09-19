package com.yym.springframework.beans.factory.config;

/**
 * @Description: Bean的引用
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 10:39
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
