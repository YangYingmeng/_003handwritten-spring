package com.yym.spring.framework.aop.config;

import lombok.Data;

/**
 * AOP 配置类
 */
@Data
public class YymAopConfig {

    private String pointCut;
    private String aspectClass;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;
}
