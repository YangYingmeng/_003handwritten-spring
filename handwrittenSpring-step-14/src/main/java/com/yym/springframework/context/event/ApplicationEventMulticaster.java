package com.yym.springframework.context.event;

import com.yym.springframework.context.ApplicationEvent;
import com.yym.springframework.context.ApplicationListener;

/**
 * @Description: 事件广播器(事件驱动模型) 事件发布器将事件通知给事件广播器, 事件广播器再将事件通知给所有已注册的事件监听器
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:19
 */
public interface ApplicationEventMulticaster {


    /**
     * @Description: 增加一个要通知所有事件的监听器
     * @Param: listener 监听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * @Description: 移除一个要通知所有事件的监听器
     * @Param: listener 监听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * @Description: 将给定的应用事件 广播 到监听器
     * @Param: listener 监听器
     */
    void multicastEvent(ApplicationEvent event);
}
