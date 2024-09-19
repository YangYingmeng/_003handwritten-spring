package com.yym.springframework;

import org.junit.Test;

import com.yym.springframework.bean.UserDao;
import com.yym.springframework.bean.UserService;
import com.yym.springframework.factory.config.BeanDefinition;
import com.yym.springframework.factory.config.BeanReference;
import com.yym.springframework.factory.support.DefaultListableBeanFactory;

/**
 * @Description: 测试 实际开发中, 开发者会将 2 3 4 会放到配置文件中实现, 下一步优化 将这些操作放到配置文件中
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 13:48
 */
public class ApiTest {

    @Test
    public void test_beanFactory() {
        // 1.创建beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. userDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        // 3. userService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        // 4. userService注入bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));
        // 5. userService获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryInfo();
    }

    @Test
    public void test1() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryInfo();
    }
}
