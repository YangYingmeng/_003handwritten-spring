package com.yym.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

import cn.hutool.core.lang.Assert;

/**
 * @Description: 将 classPath URL 及 文件三种不同类型的资源处理方式进行包装
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 16:14
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        // ClassPath
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // URL
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                // File
                return new FileSystemResource(location);
            }
        }
    }
}
