package com.yym.springframework.beans;

/**
 * @Description: 获取bean异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 20:33
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
