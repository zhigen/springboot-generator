package com.zglu.generator.generator;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件生成工具
 *
 * @author zglu
 */
@Log4j2
public class FileUtils {

    private FileUtils() {
        //工具类，不给new
    }

    /**
     * 获取模版内容
     *
     * @param first 目录
     * @param more  文件
     * @return 模版内容
     * @throws IOException io异常
     */
    public static String getTemplate(String first, String more) throws IOException {
        Stream<String> stream = null;
        try {
            Path path = Paths.get(first, more);
            stream = Files.lines(path, StandardCharsets.UTF_8);
            return stream.collect(Collectors.joining("\n"));
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * 创建目标文件或文件夹
     *
     * @param first 目录
     * @param more  文件
     * @param isDir 是否为文件夹
     * @return 文件路径
     * @throws IOException io异常
     */
    public static Path create(String first, String more, boolean isDir) throws IOException {
        Path path = Paths.get(first, more);
        if (!path.toFile().exists()) {
            if (isDir) {
                return Files.createDirectory(path);
            } else {
                return Files.createFile(path);
            }
        }
        return path;
    }

    /**
     * 写入内容
     *
     * @param path    文件路径
     * @param content 内容
     * @throws IOException io异常
     */
    public static void write(Path path, String content) throws IOException {
        Files.write(path, content.getBytes());
    }

    /**
     * 创建多级文件夹
     *
     * @param first 文件起始路径
     * @param mores 多级目录
     * @return 生成后文件目录
     * @throws IOException io异常
     */
    public static String createDir(String first, String... mores) throws IOException {
        for (String more : mores) {
            first = create(first, more, true).toString();
        }
        return first;
    }

}
