package com.yym.springframework.context;

/**
 * @Description: 事件发布接口, 所有的接口都是从这发出
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 13:27
 */
public interface ApplicationEventPublisher {


    /**
     * @Description: 事件发布
     * @Param: event 事件
     */
    void publishEvent(ApplicationEvent event);
}
