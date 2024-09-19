package com.yym.springframework.test.event;

import com.yym.springframework.context.ApplicationListener;
import com.yym.springframework.context.event.ContextRefreshedEvent;

/**
 * @Description: 容器刷新监听器
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/9 9:30
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件: " + this.getClass().getName());
    }
}
