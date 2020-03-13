package com.zglu.generator;

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
    private String first;
    private String database;
    private String[] tables;
    private String nullableTrue;
    private String defaultBitTrue;
    private Map<String, String> field;
    private Map<String, String> importMap;
    private Map<String, String> valueMap;
}
