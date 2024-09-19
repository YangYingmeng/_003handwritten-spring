package com.yym.springframework.test;

import org.junit.Test;

import com.yym.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yym.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.yym.springframework.context.support.ClassPathXmlApplicationContext;
import com.yym.springframework.test.bean.UserService;
import com.yym.springframework.test.common.MyBeanFactoryPostProcessor;
import com.yym.springframework.test.common.MyBeanPostProcessor;

/**
 * @Description: 测试类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 11:23
 */
public class ApiTest {

    // 不应用上下文
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 1. 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件 & 注册bean
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classPath:spring.xml");

        // 3. beanDefinition 加载完成 & bean实例化之前, 修改beanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. bean实例化后, 修改 bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService");
        String s = userService.queryUserInfo();
        System.out.println("测试结果: " + s);
    }

    // 应用上下文
    @Test
    public void test_xml() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classPath:springPostProcessor.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        System.out.println("测试结果: " +  userService.queryUserInfo());
    }
}
