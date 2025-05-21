package com.yym.spring.framework.context;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yym.spring.framework.annotation.YymAutowired;
import com.yym.spring.framework.annotation.YymController;
import com.yym.spring.framework.annotation.YymService;
import com.yym.spring.framework.beans.YymBeanWrapper;
import com.yym.spring.framework.beans.config.YymBeanDefinition;
import com.yym.spring.framework.beans.support.YymBeanDefinitionReader;

/**
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2025/5/20 17:53
 */
public class YymApplicationContext {

    // 存储BeanDefinition
    protected final Map<String, YymBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    // 单例IOC缓存容器, 原始 Bean 实例池
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    // 通用IOC容器, 存储 Bean 包装器, 包含bean的元数据(如原始 class、注解信息、依赖项等)
    private Map<String, YymBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    private String[] configLocations;
    private YymBeanDefinitionReader beanDefinitionReader;

    public YymApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            // 1. 定位配置文件
            beanDefinitionReader = new YymBeanDefinitionReader(configLocations);

            // 2. 加载配置文件, 将对应的类组装成BeanDefinition
            List<YymBeanDefinition> beanDefinitions = beanDefinitionReader.loadBeanDefinitions();

            // 3. 注册, 将BeanDefinition注册到容器(伪IOC容器) 至此容器初始化结束
            doRegisterBeanDefinition(beanDefinitions);

            // 4. 完成自动依赖注入
            doAutowrited();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将BeanDefinition注册到容器(伪IOC容器)
     */
    private void doRegisterBeanDefinition(List<YymBeanDefinition> beanDefinitions) throws Exception {

        for (YymBeanDefinition beanDefinition : beanDefinitions) {
            // 防止重复加载
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + "is exists");
            }

            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
            this.beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
        }
    }

    /**
     * 完成自动依赖注入
     * 本例只涉及非延时加载情况
     */
    private void doAutowrited() {
        for (Map.Entry<String, YymBeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            try {
                getBean(beanName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    /**
     * 正式开始依赖注入 DI
     * 1. 读取 BeanDefinition 中的元数据信息（如类名、构造函数、依赖等）
     * 2. 通过反射创建 bean 的实例对象
     * 3. 使用 BeanWrapper 对 bean 进行封装，以便后续属性填充和类型转换等操作
     * <p>
     * 这也就是平时使用 applicationContext.getBean(beanName)获取bean的方法
     * <p>
     * 此处用到装饰器模式, 为后续AOP提供支持
     */
    public Object getBean(String beanName) throws Exception {

        // 1. 读取 BeanDefinition 中的元数据信息
        YymBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        Object instance = null;

        // 2. 实例化
        instance = instantiateBean(beanName, beanDefinition);

        // 3. 使用装饰器模式对 instance 进行装饰
        YymBeanWrapper beanWrapper = new YymBeanWrapper(instance);

        // 4. 将 BeanWrapper 存储到 IOC 容器中
        factoryBeanInstanceCache.put(beanName, beanWrapper);

        // 5. 执行依赖注入
        populateBean(beanName, beanDefinition, beanWrapper);

        return beanWrapper.getWrapperInstance();
    }


    /**
     * 创建真正的实例对象
     */
    private Object instantiateBean(String beanName, YymBeanDefinition beanDefinition) {

        String className = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)) {
                instance = this.factoryBeanObjectCache.get(beanName);
            } else {
                Class<?> clazz = Class.forName(className);
                //2、默认的类名首字母小写
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 真正的依赖注入 DI
     */
    private void populateBean(String beanName, YymBeanDefinition beanDefinition, YymBeanWrapper beanWrapper) {

        //可能涉及到循环依赖？
        //A{ B b}
        //B{ A b}
        //用两个缓存，循环两次
        //1、把第一次读取结果为空的BeanDefinition存到第一个缓存
        //2、等第一次循环之后，第二次循环再检查第一次的缓存，再进行赋值

        Object instance = beanWrapper.getWrapperInstance();
        // 注入当前类的成员属性 A{ B b} 实际B就是A的成员变量, 需要通过A的class对象获取到对应的字段进行注入
        Class<?> clazz = beanWrapper.getWrappedClass();

        //在Spring中@Component  只有 Autowired 注解标记的字段需要注入
        if (!(clazz.isAnnotationPresent(YymController.class) || clazz.isAnnotationPresent(YymService.class))) {
            return;
        }

        // 把所有的包括private/protected/default/public 修饰字段都取出来
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(YymAutowired.class)) {
                continue;
            }

            YymAutowired autowired = field.getAnnotation(YymAutowired.class);

            //如果用户没有自定义的beanName，就默认根据类型注入
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                //field.getType().getName() 获取字段的类型
                autowiredBeanName = field.getType().getName();
            }

            //暴力访问
            field.setAccessible(true);

            try {
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                    continue;
                }
                //ioc.get(beanName) 相当于通过接口的全名拿到接口的实现的实例
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
