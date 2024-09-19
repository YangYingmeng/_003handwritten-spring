package com.yym.springframework.beans.factory;

/**
 * @Description: 销毁方法的接口
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 16:45
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
