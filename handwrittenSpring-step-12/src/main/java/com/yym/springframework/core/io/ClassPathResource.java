package com.yym.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.yym.springframework.util.ClassUtils;

import cn.hutool.core.lang.Assert;

/**
 * @Description: 从类路径（classpath）中加载资源文件
 *                  通过classLoader 读取classPath下的文件信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 15:21
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not null");
        this.path = path;
        this.classLoader = (classLoader != null) ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it not exist");
        }
        return is;
    }
}
