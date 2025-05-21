package com.yym.spring.framework.aop.support;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yym.spring.framework.aop.aspect.YymAdvice;
import com.yym.spring.framework.aop.config.YymAopConfig;


/**
 * 核心 AOP 支持类：封装切点、目标对象、通知方法等配置，并完成配置的解析。
 * 作用类似于 Spring 中的 AdvisedSupport，主要职责：
 * - 解析配置中的 pointCut 表达式
 * - 生成目标类与通知方法（Advice）的映射关系
 * - 判断目标类是否匹配切点规则
 * - 获取目标类中方法对应的通知集合
 */
public class YymAdvisedSupport {

    // AOP 配置类，包含切点表达式、切面类、通知方法名等
    private YymAopConfig config;

    // 目标对象实例
    private Object target;

    // 目标对象的 Class 类型
    private Class targetClass;

    // 用于匹配类名的切点正则表达式（从配置中解析得到）
    private Pattern pointCutClassPattern;

    // 方法缓存：目标类中的每个方法对应的通知集合
    private Map<Method, Map<String, YymAdvice>> methodCache;

    // 构造方法，注入配置类
    public YymAdvisedSupport(YymAopConfig config) {
        this.config = config;
    }

    /**
     * pointCut=public .* com.yym.demo.service..*Service..*(.*)
     * 解析配置：将配置的切点表达式转换为 Java 正则表达式，
     * 并构建目标类方法与通知方法之间的对应关系缓存。
     */
    private void parse() {
        // 将 pointCut 表达式转换为正则表达式
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")        // 点转义
                .replaceAll("\\\\.\\*", ".*")       // .* 保留
                .replaceAll("\\(", "\\\\(")         // 括号转义
                .replaceAll("\\)", "\\\\)");

        // 提取用于匹配类的正则 (com.yym.demo.service..*Service) 提取用于校验业务代码中的切点
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        // 通过该正则对具体切点进行校验
        pointCutClassPattern = Pattern.compile(
                "class " + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1)
        );

        // 初始化方法缓存
        methodCache = new HashMap<>();

        // 编译方法匹配的正则表达式
        Pattern pointCutPattern = Pattern.compile(pointCut);

        try {
            // 加载切面类
            Class aspectClass = Class.forName(this.config.getAspectClass());

            // 存储切面类中方法名 -> Method 的映射
            Map<String, Method> aspectMethods = new HashMap<>();
            for (Method method : aspectClass.getMethods()) {
                aspectMethods.put(method.getName(), method);
            }

            // 遍历目标类的方法，根据正则匹配符合切点的方法
            for (Method method : this.targetClass.getMethods()) {
                String methodString = method.toString();

                // 去掉 throws 关键字后的部分
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }

                // 匹配切点表达式
                Matcher matcher = pointCutPattern.matcher(methodString);
                if (matcher.matches()) {
                    // 初始化该方法的通知集合
                    Map<String, YymAdvice> advices = new HashMap<>();

                    // 添加前置通知
                    if (config.getAspectBefore() != null && !"".equals(config.getAspectBefore())) {
                        advices.put("before", new YymAdvice(
                                aspectClass.newInstance(),
                                aspectMethods.get(config.getAspectBefore())
                        ));
                    }

                    // 添加后置通知
                    if (config.getAspectAfter() != null && !"".equals(config.getAspectAfter())) {
                        advices.put("after", new YymAdvice(
                                aspectClass.newInstance(),
                                aspectMethods.get(config.getAspectAfter())
                        ));
                    }

                    // 添加异常通知
                    if (config.getAspectAfterThrow() != null && !"".equals(config.getAspectAfterThrow())) {
                        YymAdvice advice = new YymAdvice(
                                aspectClass.newInstance(),
                                aspectMethods.get(config.getAspectAfterThrow())
                        );
                        advice.setThrowName(config.getAspectAfterThrowingName());
                        advices.put("afterThrow", advice);
                    }

                    // 方法与通知的映射关系保存进缓存
                    methodCache.put(method, advices);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目标类中某个方法对应的通知集合。
     * 使用缓存提升性能，支持根据签名再查找一次。
     */
    public Map<String, YymAdvice> getAdvices(Method method, Object o) throws Exception {
        Map<String, YymAdvice> cache = methodCache.get(method);
        if (cache == null) {
            // 可能是由于 method 不是同一实例，通过反射重新查找
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cache = methodCache.get(m);
            this.methodCache.put(m, cache);
        }
        return cache;
    }

    /**
     * 判断目标类是否匹配切点规则，即是否需要创建代理。
     * 在 IoC 初始化时调用，用于判断是否应用 AOP。
     * 这边需要区分: pointCutClassPattern 的正则规则是由aop配置文件生成的
     * this.targetClass 是当前实例
     * 这2个对比才能判断是否符合aop
     */
    public boolean pointCutMath() {
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    // setter 和 getter 方法
    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse(); // 设置目标类后立即解析配置
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
