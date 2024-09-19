package com.yym.springframework.context;

import com.yym.springframework.beans.factory.HierarchicalBeanFactory;
import com.yym.springframework.beans.factory.ListableBeanFactory;
import com.yym.springframework.core.io.ResourceLoader;

/**
 * @Description: 应用上下文
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:46
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
