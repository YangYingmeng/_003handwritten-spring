package com.yym.springframework.bean;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 13:53
 */
public class UserService {

    private String userId;

    private UserDao userDao;

    public void queryInfo() {
        System.out.println("查询用户信息: " + userDao.queryUserName(userId));
    }

    public String getUserId() {
        return userId;
    }

    public UserService setUserId(String userId) {
        this.userId = userId;
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
