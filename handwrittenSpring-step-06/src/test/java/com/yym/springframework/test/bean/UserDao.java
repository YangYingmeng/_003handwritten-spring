package com.yym.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-01 11:17
 */
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "大猛哥");
        map.put("10002", "大猛哥哥");
        map.put("10003", "大猛哥哥哥");
    }

    public String queryUserName(String uId) {
        return map.get(uId);
    }
}
