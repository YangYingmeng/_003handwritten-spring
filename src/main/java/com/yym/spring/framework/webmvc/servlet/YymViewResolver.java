package com.yym.spring.framework.webmvc.servlet;

import java.io.File;

/**
 * 视图解析器：用于根据视图名（如 "hello"）找到对应的 HTML 模板文件
 */
public class YymViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html"; // 默认模板文件后缀
    private File tempateRootDir; // 模板根目录

    /**
     * 构造方法，传入模板根目录（如：templates）
     *
     * @param templateRoot 类路径下模板根目录
     */
    public YymViewResolver(String templateRoot) {
        // 通过类加载器获取模板文件目录路径
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        this.tempateRootDir = new File(templateRootPath);
    }

    /**
     * 根据视图名解析出具体的 YymView 对象
     *
     * @param viewName 控制器返回的逻辑视图名
     * @return YymView 视图对象
     */
    public YymView resolveViewName(String viewName) {
        if (viewName == null || "".equals(viewName.trim())) {
            return null;
        }

        // 补全文件后缀（如：index → index.html）
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX)
                ? viewName
                : (viewName + DEFAULT_TEMPLATE_SUFFIX);

        // 拼接文件路径并构造模板文件
        File templateFile = new File((tempateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));

        return new YymView(templateFile);
    }
}

