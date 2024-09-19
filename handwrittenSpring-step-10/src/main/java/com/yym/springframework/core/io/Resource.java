package com.yym.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 资源加载接口定义和实现
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 15:17
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
