package com.yym.springframework.test.event;

import java.util.Date;

import com.yym.springframework.context.ApplicationListener;

/**
 * @Description: 用户自定义监听器
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/9 11:01
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到: " + event.getSource() + "消息;时间: " + new Date());
        System.out.println("消息: " + event.getId() + ":" + event.getMessage());
    }
}
