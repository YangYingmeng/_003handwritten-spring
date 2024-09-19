package com.yym.springframework.context.event;

import com.yym.springframework.beans.factory.BeanFactory;
import com.yym.springframework.context.ApplicationEvent;
import com.yym.springframework.context.ApplicationListener;

/**
 * @Description: 广播器广播事件
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/8 15:34
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
