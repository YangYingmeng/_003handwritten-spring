package com.yym.spring.framework.webmvc.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yym.spring.framework.context.YymApplicationContext;

/**
 * 委派模式
 * 职责：负责任务调度，请求分发
 * Servlet spring入口
 */
public class YymDispatcherServlet extends HttpServlet {

    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private YymApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化spring IOC 核心容器
        applicationContext = new YymApplicationContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
