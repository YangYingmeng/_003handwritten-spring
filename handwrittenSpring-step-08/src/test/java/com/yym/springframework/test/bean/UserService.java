package com.yym.springframework.test.bean;


import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.BeanClassLoaderAware;
import com.yym.springframework.beans.factory.BeanFactory;
import com.yym.springframework.beans.factory.BeanFactoryAware;
import com.yym.springframework.beans.factory.BeanNameAware;
import com.yym.springframework.context.ApplicationContext;
import com.yym.springframework.context.ApplicationContextAware;


/**
 * @Description: 测试 bean的初始化和销毁
 */
public class UserService implements BeanFactoryAware, BeanNameAware, ApplicationContextAware, BeanClassLoaderAware {

    private String userId;
    private String company;
    private String location;
    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public String queryUserInfo() {
        return userDao.queryUserName(userId) + "," + company + "," + location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("beanName：" + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
