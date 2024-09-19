package com.yym.springframework.test.event;

import com.yym.springframework.context.event.ApplicationContextEvent;

/**
 * @Description: 用户自定义事件
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/9 9:31
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public CustomEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CustomEvent setMessage(String message) {
        this.message = message;
        return this;
    }
}
