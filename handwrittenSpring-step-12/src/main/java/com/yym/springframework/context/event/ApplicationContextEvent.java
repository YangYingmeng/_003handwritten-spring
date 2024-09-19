package com.yym.springframework.context.event;

import com.yym.springframework.context.ApplicationContext;
import com.yym.springframework.context.ApplicationEvent;

/**
 * @Description: 上下文事件 所有上下文相关事件 继承该类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:11
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
