package com.yym.spring.framework.beans.support;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yym.spring.framework.beans.config.YymBeanDefinition;

/**
 * 从各种配置资源中（如 XML、Properties、注解等）读取 Bean 定义信息，
 * 并生成 BeanDefinition 放入 BeanFactory（或 ApplicationContext）中。
 * 有各种子实现 如 XmlBeanDefinitionReader 等, 目前主流是将配置类中的bean转为BeanDefinition
 */
public class YymBeanDefinitionReader {

    //保存扫描到的beanName
    private List<String> regitryBeanClasses = new ArrayList<>();
    // 将配置文件加载为Properties对象
    private Properties contextConfig = new Properties();

    public YymBeanDefinitionReader(String... configLocations) {
        doLoadConfig(configLocations[0]);

        //读取配置文件中需要扫描的路径
        doScanner(contextConfig.getProperty("scanPackage"));
    }


    public Properties getConfig(){
        return this.contextConfig;
    }

    /**
     * 将配置文件转为 Properties对象
     */
    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replaceAll("classpath:",""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描路径, 将路径下所有的全类名保存至 regitryBeanClasses
     */
    private void doScanner(String scanPackage) {
        //jar 、 war 、zip 、rar
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());

        //当成是一个ClassPath文件夹
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                //全类名 = 包名.类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                regitryBeanClasses.add(className);
            }
        }
    }

    /**
     * 加载BeanDefinitions
     */
    public List<YymBeanDefinition> loadBeanDefinitions() {
        List<YymBeanDefinition> result = new ArrayList<YymBeanDefinition>();
        try {
            // 1. 遍历所有扫描路径下的 类全名
            for (String className : regitryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                // 2. 如果是接口跳过, 因为接口无法实例化
                if(beanClass.isInterface()){continue;}
                // 3. 如果是类, 保存类对应的ClassName（全类名）, beanName 默认是类名首字母小写
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                // 4. 如果类有父接口, 需要建立接口名 → 实现类名的映射 因为在注入时往往是面向接口编程 注入的是接口
                for (Class<?> i : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefinition(i.getName(),beanClass.getName()));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private YymBeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        YymBeanDefinition beanDefinition = new YymBeanDefinition();
        beanDefinition.setFactoryBeanName(beanName);
        beanDefinition.setBeanClassName(beanClassName);
        return beanDefinition;
    }


    /**
     * 类首字母小写, 简单写法
     */
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
