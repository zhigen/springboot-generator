package com.zglu.generator.generator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author zglu
 */
@Data
@Configuration
@EnableConfigurationProperties({GeneratorConfig.class})
@ConfigurationProperties(prefix = "generator.config")
public class GeneratorConfig {
    private String first = System.getProperty("user.dir");
    private String database;
    private String[] tables;
    private String author;
    private String templatePath = first + "/src/main/resources/template";
    private String targetPath = first + "/src/main/java/com/zglu/generator";
    private String targetDir;
    private String nullableTrue;
    private String defaultBitTrue;
    private String sqlLineFeed = ", \" +\n            \"";
    private int insertSqlLineSize = 5;
    private Map<String, String> field;
    private Map<String, String> importMap;
    private Map<String, String> valueMap;
}
