package com.yym.springframework.context;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.Aware;

/**
 * @Description: 上下文感知类, 实现改接口可以感知到所属的applicationContext
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-02 14:25
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
