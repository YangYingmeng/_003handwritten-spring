package com.yym.spring.framework.webmvc.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yym.spring.framework.annotation.YymController;
import com.yym.spring.framework.annotation.YymRequestMapping;
import com.yym.spring.framework.context.YymApplicationContext;

/**
 * 前端控制器
 * 委派模式
 * 职责：负责任务调度，请求分发
 * Servlet spring入口
 */
public class YymDispatcherServlet extends HttpServlet {

    private YymApplicationContext applicationContext;

    private List<YymHandlerMapping> handlerMappings = new ArrayList<YymHandlerMapping>();

    private Map<YymHandlerMapping, YymHandlerAdapter> handlerAdapters = new HashMap<YymHandlerMapping, YymHandlerAdapter>();

    private List<YymViewResolver> viewResolvers = new ArrayList<YymViewResolver>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化spring IOC 核心容器
        applicationContext = new YymApplicationContext();

        //初始化九大组件
        initStrategies(applicationContext);

        System.out.println("Yym Spring framework is init.");
    }

    private void initStrategies(YymApplicationContext context) {
        // 多文件上传的组件
        // initMultipartResolver(context);
        // 初始化本地语言环境
        // initLocaleResolver(context);
        // 初始化模板处理器
        // initThemeResolver(context);

        // 初始化处理器映射器
        initHandlerMappings(context);
        // 初始化参数适配器
        initHandlerAdapters(context);

        // 初始化异常拦截器
        // initHandlerExceptionResolvers(context);

        //初始化视图预处理器
        // initRequestToViewNameTranslator(context);

        // 初始化视图转换器
        initViewResolvers(context);

        // FlashMap管理器
        // initFlashMapManager(context);
    }

    private void initHandlerMappings(YymApplicationContext context) {

        if (this.applicationContext.getBeanDefinitionCount() == 0) {
            return;
        }

        for (String beanName : this.applicationContext.getBeanDefinitionNames()) {

            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();

            if (!clazz.isAnnotationPresent(YymController.class)) {
                continue;
            }

            //相当于提取 class 上配置的url
            String baseUrl = "";
            if (clazz.isAnnotationPresent(YymRequestMapping.class)) {
                YymRequestMapping requestMapping = clazz.getAnnotation(YymRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只获取public的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(YymRequestMapping.class)) {
                    continue;
                }
                // 提取每个方法上面配置的url 相当于@PostMapping 标记的url
                YymRequestMapping requestMapping = method.getAnnotation(YymRequestMapping.class);

                // 将具体方法的url通过正则和对应的方法 对应的web类进行映射
                String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMappings.add(new YymHandlerMapping(pattern, instance, method));
                System.out.println("Mapped : " + regex + "," + method);
            }

        }
    }

    private void initHandlerAdapters(YymApplicationContext context) {
        // 通过简单的方式Map, 将映射器和适配器做映射, 这样servlet不会直接调用映射器, 而是通过适配器进行调用
        for (YymHandlerMapping handlerMapping : handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new YymHandlerAdapter());
        }
    }

    private void initViewResolvers(YymApplicationContext context) {

        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new YymViewResolver(templateRoot));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 委派,根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req, resp);

        } catch (Exception e) {
            try {

                processDispatchResult(req, resp, new YymModelAndView("500"));
            } catch (Exception e1) {
                e1.printStackTrace();
                resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //1、通过URL获得一个HandlerMapping
        YymHandlerMapping handler = getHandler(req);
        if (handler == null) {
            processDispatchResult(req, resp, new YymModelAndView("404"));
            return;
        }

        //2、根据一个HandlerMaping获得一个HandlerAdapter
        YymHandlerAdapter adapter = getHandlerAdapter(handler);

        //3、解析某一个方法的形参和返回值之后，统一封装为ModelAndView对象
        // 将该HandlerMaping 作为构造参数传入适配器, 在适配器中调用具体的Controller方法
        YymModelAndView mv = adapter.handler(req, resp, handler);

        // 就把ModelAndView变成一个ViewResolver
        processDispatchResult(req, resp, mv);

    }

    /**
     * 找到对应的 Handler（Controller + Method）
     * 此处只是找对应的Handler
     * 在调用Handler的方法时, 才需要适配器
     */
    private YymHandlerMapping getHandler(HttpServletRequest req) {

        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        // contextPath 是项目访问的根路径 http://localhost:8080/myapp/user/query
        // /myapp/user/query
        String url = req.getRequestURI();
        // /myapp
        String contextPath = req.getContextPath();
        // /user/query , 因为handlerMapping 中存的是相对路径
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        // 获取对应的handlerMapping, 通过url的正则映射
        for (YymHandlerMapping mapping : handlerMappings) {
            Matcher matcher = mapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return mapping;
        }
        return null;
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, YymModelAndView mv) throws Exception {
        if (null == mv) {
            return;
        }
        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (YymViewResolver viewResolver : this.viewResolvers) {
            YymView view = viewResolver.resolveViewName(mv.getViewName());
            //直接往浏览器输出
            view.render(mv.getModel(), req, resp);
            return;
        }
    }

    private YymHandlerAdapter getHandlerAdapter(YymHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(handler);
    }
}
