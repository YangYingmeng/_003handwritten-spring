package com.yym.springframework.util;

/**
 * @Description: class工具类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 15:39
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {

        ClassLoader cl = null;
        try {
            // 获取当前线程的上下文类加载器
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {

        }
        if (cl == null) {
            // 获取不到上下文类加载器则获取当前工具类的类加载器
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
