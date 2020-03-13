package com.zglu.generator.generator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 实体类属性对象
 *
 * @author zglu
 */
@Data
@NoArgsConstructor
public class FieldVo {
    private Set<String> needImport = new HashSet<>();
    private String tab = "    ";
    private String comment;
    private String modifier = "private";
    private String type;
    private String name;
    private String value;

    public FieldVo(Columns columns, GeneratorConfig generatorConfig) {
        this.comment = columns.getColumnComment();
        this.type = generatorConfig.getField().get(columns.getDataType());
        this.name = ReplaceUtils.getFieldName(columns.getColumnName());
        this.setValueByParams(columns.getColumnDefault(), columns.getIsNullable(), generatorConfig);
        this.addNeedImport(generatorConfig, this.type);
    }

    private void setValueByParams(String columnDefault, String isNullable, GeneratorConfig generatorConfig) {
        Map<String, String> valueMap = generatorConfig.getValueMap();
        if (columnDefault != null) {
            // 有默认值
            if (this.type.equals(Boolean.class.getSimpleName())) {
                // 属性为Boolean类型，需要将b'1'转换成true
                columnDefault = generatorConfig.getDefaultBitTrue().equals(columnDefault) + "";
            }
            this.value = valueMap.get(this.type).replace("?1", columnDefault);
            // 导入赋值类
            this.addNeedImport(generatorConfig, this.type + "-value");
        } else if (generatorConfig.getNullableTrue().equals(isNullable)) {
            // 可空
            this.value = "null";
        } else {
            // 非空
            this.value = valueMap.get(this.type + "-new");
        }
    }

    private void addNeedImport(GeneratorConfig generatorConfig, String key) {
        Map<String, String> importMap = generatorConfig.getImportMap();
        String importTemp = importMap.get(key);
        if (!StringUtils.isEmpty(importTemp)) {
            this.needImport.add(importTemp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(this.comment)) {
            sb.append(this.tab).append("/**").append("\n");
            sb.append(this.tab).append(" * ").append(this.comment).append("\n");
            sb.append(this.tab).append(" */").append("\n");
        }
        sb.append(this.tab).append(String.join(" ", this.modifier, this.type, this.name, "=", this.value + ";"));
        return sb.toString();
    }
}
