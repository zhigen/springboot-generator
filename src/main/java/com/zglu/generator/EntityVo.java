package com.zglu.generator;

import com.zglu.generator.entity.Columns;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 实体类对象
 *
 * @author zglu
 */
@Data
public class EntityVo {
    private String fileName;
    private String packageName;
    private String importString;
    private String tableName;
    private String className;
    private String fieldString;

    public EntityVo(String tableName, List<Columns> columnsList, GeneratorConfig generatorConfig) {
        this.tableName = tableName;
        this.className = ReplaceUtils.getClassName(this.tableName);
        this.fileName = className + ".java";
        this.packageName = ReplaceUtils.getPackageName(tableName);
        List<FieldVo> fieldVoList = columnsList.stream().map(m -> new FieldVo(m, generatorConfig)).collect(Collectors.toList());
        this.importString = this.getImportStringByField(fieldVoList);
        this.fieldString = this.getFieldStringByField(fieldVoList);
    }

    private String getImportStringByField(List<FieldVo> fieldVoList) {
        String temp = fieldVoList.stream().flatMap(m -> m.getNeedImport().stream()).distinct().collect(Collectors.joining("\n"));
        if (!StringUtils.isEmpty(temp)) {
            temp += "\n";
        }
        return temp;
    }

    private String getFieldStringByField(List<FieldVo> fieldVoList) {
        String temp = fieldVoList.stream().map(FieldVo::toString).collect(Collectors.joining("\n"));
        if (!StringUtils.isEmpty(temp)) {
            temp += "\n";
        }
        return temp;
    }
}
