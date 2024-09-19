package com.yym.springframework.bean;

import java.util.HashMap;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 13:50
 */
public class UserDao {

    private static HashMap<String, String> map = new HashMap<>();

    static {
        map.put("1", "大猛哥");
        map.put("2", "大猛哥哥");
        map.put("3", "大猛哥哥哥");
    }

    public String queryUserName(String userId) {
        return map.get(userId);
    }
}
