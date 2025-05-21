package com.yym.spring.framework.webmvc.servlet;

import java.util.Map;

/**
 * 处理器适配器需要返回该组件给前端, 前端根据该组件渲染页面, 放在前面实现
 */
public class YymModelAndView {

    private String viewName;
    private Map<String, ?> model;

    public YymModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public YymModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}


