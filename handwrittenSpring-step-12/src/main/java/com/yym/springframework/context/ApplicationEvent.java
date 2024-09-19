package com.yym.springframework.context;

import java.util.EventObject;

/**
 * @Description: 事件抽象类, 所有的事件继承该类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:09
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
