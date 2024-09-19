package com.yym.springframework.test.event;

import com.yym.springframework.context.ApplicationListener;
import com.yym.springframework.context.event.ContextClosedEvent;


/**
 * @Description: 关闭应用上下文监听器
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/9 9:26
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件: " + this.getClass().getName());
    }
}
