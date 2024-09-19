package com.yym.springframework.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.yym.springframework.factory.config.BeanDefinition;
import com.yym.springframework.factory.pojo.UserService;
import com.yym.springframework.factory.support.DefaultListableBeanFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/25 16:02
 */
public class ApiTest {

    @Test
    public void test() {
        // 获取spring工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 首次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "yym");
        userService.print();
        // 第二次获取bean
        UserService userService1 = (UserService) beanFactory.getBean("userService", "yym");
        userService1.print();
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create(new Class[]{String.class}, new Object[]{"Yym"});
        System.out.println(o);
    }

    @Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> beanClass = UserService.class;
        // 获取参数类型为String的构造器
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("yym");
        userService.print();
    }

    @Test
    public void test_parameterTypes() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] constructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = null;
        // 获取入参只有一个的构造函数
        for (Constructor<?> ctor : constructors) {
            if (ctor.getParameterTypes().length == 1) {
                constructor = ctor;
                break;
            }
        }
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("yym");
        userService.print();
    }
}
