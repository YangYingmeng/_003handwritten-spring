package com.yym.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yym.demo.service.IQueryService;
import com.yym.spring.framework.annotation.YymService;

import lombok.extern.slf4j.Slf4j;

/**
 * 查询业务
 */
@YymService
@Slf4j
public class QueryService implements IQueryService {

    /**
     * 查询
     */
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        log.info("这是在业务方法中打印的：" + json);
        return json;
    }

}
