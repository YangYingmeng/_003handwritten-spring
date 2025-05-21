package com.yym.spring.framework.webmvc.servlet;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图对象：用于渲染页面模板内容，替换变量，输出到前端
 */
public class YymView {

    private File viewFile; // HTML模板文件

    public YymView(File templateFile) {
        this.viewFile = templateFile;
    }

    /**
     * 渲染页面，将模板中的占位符替换为 model 中的实际数据
     *
     * @param model 传入的模型数据
     * @param req   请求对象
     * @param resp  响应对象
     * @throws Exception 异常处理
     */
    public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        StringBuffer sb = new StringBuffer();

        // 用于逐行读取模板内容
        RandomAccessFile ra = new RandomAccessFile(this.viewFile, "r");

        String line;
        while ((line = ra.readLine()) != null) {
            // 解决中文乱码问题，将文件内容从 ISO-8859-1 转成 UTF-8
            line = new String(line.getBytes("ISO-8859-1"), "utf-8");

            // 正则匹配形如 ￥{xxx} 的变量占位符（注意这里使用了 ￥ 而不是 $）
            Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);

            // 逐个查找并替换变量
            while (matcher.find()) {
                String paramName = matcher.group(); // 如 ￥{name}
                paramName = paramName.replaceAll("￥\\{|\\}", ""); // 去掉￥{ 和 }

                // 从模型中获取变量值
                Object paramValue = model.get(paramName);

                // 将变量值替换占位符，注意需要对特殊字符做转义
                line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
                matcher = pattern.matcher(line); // 重建 matcher，防止替换错位
            }

            sb.append(line);
        }

        // 写回前端响应
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(sb.toString());
    }

    /**
     * 处理正则表达式中的特殊字符，防止替换错误
     *
     * @param str 原始字符串
     * @return 转义后的字符串
     */
    public static String makeStringForRegExp(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }
}

