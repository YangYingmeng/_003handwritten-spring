package com.yym.springframework.beans.factory;

/**
 * @Description: 在Bean实例化和属性注入完成后立即调用，用于执行一些初始化操作。这些初始化操作可以包括：打开数据库连接、初始化缓存、注册JMX等等
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 16:43
 */
public interface InitializingBean {


    /**
     * @Description: Bean 处理了属性填充后调用
     */
    void afterPropertiesSet() throws Exception;
}
