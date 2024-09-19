package com.yym.springframework.test.bean;

/**
 * @Description: test
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/28 14:29
 */
public class UserService {

    private String userId;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
