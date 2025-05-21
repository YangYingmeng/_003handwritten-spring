package com.yym.demo.action;

import java.util.HashMap;
import java.util.Map;

import com.yym.demo.service.IQueryService;
import com.yym.spring.framework.annotation.YymAutowired;
import com.yym.spring.framework.annotation.YymController;
import com.yym.spring.framework.annotation.YymRequestMapping;
import com.yym.spring.framework.annotation.YymRequestParam;
import com.yym.spring.framework.webmvc.servlet.YymModelAndView;


/**
 * 公布接口url
 */
@YymController
@YymRequestMapping("/")
public class PageAction {

    @YymAutowired
    IQueryService queryService;

    @YymRequestMapping("/first.html")
    public YymModelAndView query(@YymRequestParam("user") String user) {
        String result = queryService.query(user);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        model.put("data", result);
        model.put("token", "123456");
        return new YymModelAndView("first.html", model);
    }

}
