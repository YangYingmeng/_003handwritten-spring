package com.yym.springframework.context;

import java.util.EventListener;

/**
 * @Description: 监听应用程序的事件监听器, 观察者模式
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:21
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {


    /**
     * @Description: 处理监听到的应用事件
     * @Param: event 事件内容
     */
    void onApplicationEvent(E event);
}
