package com.yym.springframework.aop.framework;

import com.yym.springframework.aop.AdvisedSupport;

/**
 * @Description: 代理工厂, 解决使用JDK动态代理还是用Cglib动态代理
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/5/12 10:29
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
