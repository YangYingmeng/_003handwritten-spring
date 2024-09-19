package com.yym.springframework.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.yym.springframework.beans.PropertyValue;
import com.yym.springframework.beans.PropertyValues;
import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.beans.factory.config.BeanReference;
import com.yym.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yym.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.yym.springframework.core.io.DefaultResourceLoader;
import com.yym.springframework.core.io.Resource;
import com.yym.springframework.test.bean.UserDao;
import com.yym.springframework.test.bean.UserService;

import cn.hutool.core.io.IoUtil;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/28 14:24
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. userDao注册
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        // 3. 为UserService赋值
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. 注入UserService
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 5. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserInfo());
    }

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/spring.xml");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

    @Test
    public void test_url() throws IOException {
        // 公开的资源URL
        Resource resource = resourceLoader.getResource("https://jsonplaceholder.typicode.com/posts/1");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() {
        // 1. 获取beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件 注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resourceLoader.getResource("src/test/resources/spring.xml"));

        // 3. 获取Bean对象
        UserService userService = (UserService) beanFactory.getBean("userService");
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userService.queryUserInfo());
        System.out.println(userDao.queryUserName("2"));
    }
}
