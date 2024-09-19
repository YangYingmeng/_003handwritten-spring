package com.yym.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @Description: 加载文件资源
 *                   通过指定文件路径的方式读取文件信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/27 15:51
 */
public class FileSystemResource implements Resource {

    private final String path;

    private final File file;

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.file.toPath());
    }

    public String getPath() {
        return path;
    }
}
