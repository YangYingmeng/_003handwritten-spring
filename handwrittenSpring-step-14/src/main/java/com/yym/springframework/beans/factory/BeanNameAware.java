package com.yym.springframework.beans.factory;

/**
 * @Description: BeanName感知类, 实现该接口可以感知到所属的BaneName
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-02 14:23
 */
public interface BeanNameAware {

    void setBeanName(String beanName);
}
