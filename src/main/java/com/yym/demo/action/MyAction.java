package com.yym.demo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yym.demo.service.IModifyService;
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
@YymRequestMapping("/web")
public class MyAction {

    @YymAutowired
    IQueryService queryService;
    @YymAutowired
    IModifyService modifyService;

    @YymRequestMapping("/query.json")
    public YymModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                 @YymRequestParam("name") String name) {
        String result = queryService.query(name);
        return out(response, result);
    }

    @YymRequestMapping("/add*.json")
    public YymModelAndView add(HttpServletRequest request, HttpServletResponse response,
                               @YymRequestParam("name") String name, @YymRequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        return out(response, result);
    }

    @YymRequestMapping("/remove.json")
    public YymModelAndView remove(HttpServletRequest request, HttpServletResponse response,
                                  @YymRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @YymRequestMapping("/edit.json")
    public YymModelAndView edit(HttpServletRequest request, HttpServletResponse response,
                                @YymRequestParam("id") Integer id,
                                @YymRequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }


    private YymModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
