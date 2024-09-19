package com.yym.springframework.test.bean;

import java.util.Random;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 13:18
 */
public class UserService implements IUserService {

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "大猛哥, 10001, 深圳";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
