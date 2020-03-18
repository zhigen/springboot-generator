package com.zglu.generator.generator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符替换工具
 *
 * @author zglu
 */
@Log4j2
public class ReplaceUtils {

    private ReplaceUtils() {
    }

    public static final Pattern PATTERN_UNDERLINE = Pattern.compile("_(\\w)");
    public static final Pattern PATTERN_UPPERCASE = Pattern.compile("[A-Z]");

    /**
     * 根据表名生成包名
     *
     * @param tableName 表名
     * @return 包名
     */
    public static String getPackageName(String tableName) {
        return tableName.replace("_", "");
    }

    /**
     * 根据表名生成url名
     *
     * @param tableName 表名
     * @return url名
     */
    public static String getUrlName(String tableName) {
        return tableName.replace("_", "-");
    }

    /**
     * 数据库字段转换成java字段
     *
     * @param columnName 字段名
     * @return 属性名
     */
    public static String getFieldName(String columnName) {
        String str = Optional.ofNullable(columnName).orElse("").toLowerCase();
        Matcher matcher = PATTERN_UNDERLINE.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * java属性名转换成数据库字段名
     *
     * @param fieldName 属性名
     * @return 字段名
     */
    public static String getColumnName(String fieldName) {
        String str = Optional.ofNullable(fieldName).orElse("");
        Matcher matcher = PATTERN_UPPERCASE.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @param letter 单词
     * @return 首字母大字单词
     */
    public static String upperFirstLatter(String letter) {
        return letter.substring(0, 1).toUpperCase() + letter.substring(1);
    }

    /**
     * 类名
     *
     * @param tableName 表名
     * @return 类名
     */
    public static String getClassName(String tableName) {
        String letter = getFieldName(tableName);
        return upperFirstLatter(letter);
    }

    /**
     * 替换
     *
     * @param content 模板内容
     * @param obj     替换对象
     * @return 替换后内容
     */
    public static String replaceContent(String content, Object obj) {
        Map<String, String> map = JSON.parseObject(JSON.toJSONString(obj), new TypeReference<Map<String, String>>() {
        });
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            content = content.replace("{" + k + "}", v);
        }
        return content;
    }
}
