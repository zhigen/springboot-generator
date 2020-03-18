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
    private String mapperUpdateAllString;
    private String mapperUpdateString;

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
        this.columnString = this.getColumnStringByColumn(columnsList, generatorConfig);
        fieldString4Mapper = this.getFieldString4MapperByField(fieldVoList, generatorConfig);
        mapperUpdateAllString = columnsList.stream().map(m -> m.getColumnName() + " = #{" + ReplaceUtils.getFieldName(m.getColumnName()) + "}").collect(Collectors.joining(generatorConfig.getSqlLineFeed()));
        mapperUpdateString = columnsList.stream().map(m -> m.getColumnName() + " = CASE WHEN #{" + ReplaceUtils.getFieldName(m.getColumnName()) + "} IS NULL THEN " + m.getColumnName() + " ELSE #{" + ReplaceUtils.getFieldName(m.getColumnName()) + "} END").collect(Collectors.joining(generatorConfig.getSqlLineFeed()));
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

    private String getColumnStringByColumn(List<Columns> columnsList, GeneratorConfig generatorConfig) {
        return columnsList.stream().map(m -> {
            if (columnsList.indexOf(m) == columnsList.size() - 1) {
                return m.getColumnName();
            } else if ((columnsList.indexOf(m) + 1) % generatorConfig.getInsertSqlLineSize() == 0) {
                return m.getColumnName() + generatorConfig.getSqlLineFeed();
            } else {
                return m.getColumnName() + ", ";
            }
        }).collect(Collectors.joining());
    }

    private String getFieldString4MapperByField(List<FieldVo> fieldVoList, GeneratorConfig generatorConfig) {
        return fieldVoList.stream().map(m -> {
            if (fieldVoList.indexOf(m) == fieldVoList.size() - 1) {
                return "#{" + m.getName() + "}";
            } else if ((fieldVoList.indexOf(m) + 1) % generatorConfig.getInsertSqlLineSize() == 0) {
                return "#{" + m.getName() + "}" + generatorConfig.getSqlLineFeed();
            } else {
                return "#{" + m.getName() + "}, ";
            }
        }).collect(Collectors.joining());
    }
}
