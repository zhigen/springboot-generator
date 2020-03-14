package com.zglu.generator.generator;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类数据对象
 *
 * @author zglu
 */
@Data
public class ClassVo {
    private String packageName;
    private String importString;
    private String author;
    private String tableName;
    private String tableComment;
    private String tableNameMid;
    private String className;
    private String valName;
    private String fieldString;
    private String columnString;
    private String fieldString4Mapper;
    private String updateString4Mapper;

    public ClassVo(String tableName, List<Columns> columnsList, GeneratorConfig generatorConfig, String tableComment) {
        this.author = generatorConfig.getAuthor();
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.tableNameMid = ReplaceUtils.getUrlName(this.tableName);
        this.className = ReplaceUtils.getClassName(this.tableName);
        this.valName = ReplaceUtils.getFieldName(this.tableName);
        this.packageName = ReplaceUtils.getPackageName(this.tableName);
        List<FieldVo> fieldVoList = columnsList.stream().map(m -> new FieldVo(m, generatorConfig)).collect(Collectors.toList());
        this.importString = this.getImportStringByField(fieldVoList);
        this.fieldString = this.getFieldStringByField(fieldVoList);
        columnString = columnsList.stream().map(Columns::getColumnName).collect(Collectors.joining(","));
        fieldString4Mapper = fieldVoList.stream().map(m -> "#{" + m.getName() + "}").collect(Collectors.joining(","));
        updateString4Mapper = columnsList.stream().map(m -> m.getColumnName() + "=#{" + ReplaceUtils.getFieldName(m.getColumnName()) + "}").collect(Collectors.joining(","));
    }

    private String getImportStringByField(List<FieldVo> fieldVoList) {
        String temp = fieldVoList.stream().flatMap(m -> m.getNeedImport().stream()).distinct().collect(Collectors.joining(";\n"));
        if (StringUtils.hasText(temp)) {
            temp += ";\n";
        }
        return temp;
    }

    private String getFieldStringByField(List<FieldVo> fieldVoList) {
        String temp = fieldVoList.stream().map(FieldVo::toString).collect(Collectors.joining("\n"));
        if (StringUtils.hasText(temp)) {
            temp += "\n";
        }
        return temp;
    }
}
