package com.yym.springframework.core.io;

/**
 * @Description: 提供统一入口获取 Resource
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 16:06
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
