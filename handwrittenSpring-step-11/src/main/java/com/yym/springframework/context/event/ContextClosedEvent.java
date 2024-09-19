package com.yym.springframework.context.event;

/**
 * @Description: 上下文关闭事件
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:14
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
