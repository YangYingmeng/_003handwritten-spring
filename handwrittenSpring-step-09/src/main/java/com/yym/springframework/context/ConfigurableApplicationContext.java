package com.yym.springframework.context;

import com.yym.springframework.beans.BeansException;

/**
 * @Description: ApplicationContext的扩展接口, 提供了更加灵活的管理spring上下文的功能
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:48
 */
public interface ConfigurableApplicationContext extends ApplicationContext {


    /**
     * @Description: 刷新容器
     */
    void refresh() throws BeansException;


    /**
     * @Description: 注册虚拟机钩子
     */
    void registerShutdownHook();


    /**
     * @Description: 手动执行关闭
     */
    void close();
}
