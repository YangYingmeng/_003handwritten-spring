package com.yym.springframework.test.bean;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 11:20
 */
public class UserService {

    private String userId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(userId) + "," + company + "," + location;
    }

    public String getUserId() {
        return userId;
    }

    public UserService setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public UserService setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserService setLocation(String location) {
        this.location = location;
        return this;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserService setUserDao(UserDao userDao) {
        this.userDao = userDao;
        return this;
    }
}
