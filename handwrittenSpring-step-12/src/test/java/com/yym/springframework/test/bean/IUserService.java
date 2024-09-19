package com.yym.springframework.test.bean;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 13:17
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}
