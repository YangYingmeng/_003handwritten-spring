package com.yym.springframework.context.event;

/**
 * @Description: 上下文刷新事件
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:16
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
