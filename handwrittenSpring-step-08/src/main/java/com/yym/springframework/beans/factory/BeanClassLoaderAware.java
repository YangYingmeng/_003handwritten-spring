package com.yym.springframework.beans.factory;

/**
 * @Description: classLoader感知类, 实现该接口可以感知到所属的classLoader
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-02 14:21
 */
public interface BeanClassLoaderAware {

    void setBeanClassLoader(ClassLoader classLoader);
}
