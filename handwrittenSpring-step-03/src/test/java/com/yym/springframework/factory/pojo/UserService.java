package com.yym.springframework.factory.pojo;

/**
 * @Description: 测试案例
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/25 16:02
 */
public class UserService {
    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }

    public String getName() {
        return name;
    }

    public UserService setName(String name) {
        this.name = name;
        return this;
    }

    public void print() {
        System.out.println(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}
