package com.yym.spring.framework.webmvc.servlet;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yym.spring.framework.annotation.YymRequestParam;

/**
 * 前端控制器 本身不会调用Controller, 会通过匹配对应的HandlerAdapter来执行对应的Controller
 */
public class YymHandlerAdapter {

    /**
     * 执行 Handler 对应的方法，并封装成 YymModelAndView 结果返回
     *
     * @param req     HTTP 请求对象
     * @param resp    HTTP 响应对象
     * @param handler 封装了 Controller 类和目标方法的映射信息
     * @return YymModelAndView 结果视图对象
     */
    public YymModelAndView handler(HttpServletRequest req, HttpServletResponse resp, YymHandlerMapping handler) throws Exception {

        // public String hello(@YymRequestParam("name") String name, HttpServletRequest req, HttpServletResponse resp)
        // 以这个方法为例, 需要构建业务参数 以及 req resp参数, 才能正确调用controller.hello()方法

        // 1. 构建参数名与参数位置的映射，例如：name -> 0
        Map<String, Integer> paramIndexMapping = new HashMap<>();

        // 2. 解析方法参数上的 @YymRequestParam 注解，填充 paramIndexMapping
        Annotation[][] pa = handler.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if (a instanceof YymRequestParam) {
                    String paramName = ((YymRequestParam) a).value();
                    if (!paramName.trim().isEmpty()) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        // 3. 记录 HttpServletRequest 和 HttpServletResponse 参数的位置
        Class<?>[] paramTypes = handler.getMethod().getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> type = paramTypes[i];
            if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }

        // 4. 构造方法调用时的参数值数组 paramValues
        Map<String, String[]> requestParams = req.getParameterMap();
        Object[] paramValues = new Object[paramTypes.length];

        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String paramName = entry.getKey();
            String value = Arrays.toString(entry.getValue())
                    .replaceAll("\\[|\\]", "")  // 去除中括号
                    .replaceAll("\\s+", ",");   // 空格换成逗号，简化处理

            if (!paramIndexMapping.containsKey(paramName)) continue;

            int index = paramIndexMapping.get(paramName);
            paramValues[index] = castStringValue(value, paramTypes[index]);
        }

        // 5. 设置 HttpServletRequest 和 HttpServletResponse 参数
        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }
        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = resp;
        }

        // 6. 通过反射调用 Controller 方法
        Object result = handler.getMethod().invoke(handler.getController(), paramValues);

        // 7. 处理方法返回值
        if (result == null || result instanceof Void) return null;

        // 返回值为 YymModelAndView 时直接返回
        if (handler.getMethod().getReturnType() == YymModelAndView.class) {
            return (YymModelAndView) result;
        }

        // 其他返回类型暂不支持，返回 null
        return null;
    }


    /**
     * 简单的参数类型转换器：String -> Integer/Double/String
     */
    private Object castStringValue(String value, Class<?> paramType) {
        if (String.class == paramType) {
            return value;
        } else if (Integer.class == paramType || int.class == paramType) {
            return Integer.valueOf(value);
        } else if (Double.class == paramType || double.class == paramType) {
            return Double.valueOf(value);
        } else {
            // 其他类型暂不处理，直接返回字符串或 null
            return value != null ? value : null;
        }
    }
}
