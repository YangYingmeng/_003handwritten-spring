package com.yym.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: test
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/28 14:28
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("1", "大猛哥");
        hashMap.put("2", "大猛哥哥");
        hashMap.put("3", "大猛哥哥哥");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
