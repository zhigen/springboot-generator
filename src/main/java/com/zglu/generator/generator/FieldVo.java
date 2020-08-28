package com.zglu.generator.generator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 属性数据对象
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
    private boolean required;

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
            // 非空时，不帮初始默认值了，通过sql报错提醒未设值字段
            this.value = "null";
        }
        required = !generatorConfig.getNullableTrue().equals(isNullable);
    }

    private void addNeedImport(GeneratorConfig generatorConfig, String key) {
        Map<String, String> importMap = generatorConfig.getImportMap();
        String importTemp = importMap.get(key);
        if (StringUtils.hasText(importTemp)) {
            this.needImport.add(importTemp);
        }
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        if (StringUtils.hasText(this.comment)) {
//            sb.append(this.tab).append("/**").append("\n");
//            sb.append(this.tab).append(" * ").append(this.comment).append("\n");
//            sb.append(this.tab).append(" */").append("\n");
//        }
//        sb.append(this.tab).append(String.join(" ", this.modifier, this.type, this.name, "=", this.value + ";"));
//        return sb.toString();
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.hasText(this.comment)) {
            sb.append(this.tab).append("/**").append("\n");
            sb.append(this.tab).append(" * ").append(this.comment).append("\n");
            sb.append(this.tab).append(" */").append("\n");
        }
        if ("createdDate".equals(this.name)) {
            sb.append(this.tab).append("@TableField(fill = FieldFill.INSERT)").append("\n");
        } else if ("lastModifiedDate".equals(this.name)) {
            sb.append(this.tab).append("@TableField(fill = FieldFill.INSERT_UPDATE)").append("\n");
        } else if ("enable".equals(this.name)) {
            sb.append(this.tab).append("@TableField(fill = FieldFill.INSERT)").append("\n");
            sb.append(this.tab).append("@TableLogic(value = \"1\", delval = \"0\")").append("\n");
        } else if ("deleted".equals(this.name)) {
            sb.append(this.tab).append("@TableField(fill = FieldFill.INSERT)").append("\n");
            sb.append(this.tab).append("@TableLogic(value = \"0\", delval = \"1\")").append("\n");
        }
        sb.append(this.tab).append(String.join(" ", this.modifier, this.type, this.name + ";"));
        return sb.toString();
    }

    public String toDtoString() {
        StringBuilder sb = new StringBuilder();
        if (required) {
            sb.append(this.tab).append("@ApiModelProperty(value = \"").append(this.comment).append("\", required = true)").append("\n");
        } else {
            sb.append(this.tab).append("@ApiModelProperty(\"").append(this.comment).append("\")").append("\n");
        }
        sb.append(this.tab).append(String.join(" ", this.modifier, this.type, this.name + ";"));
        return sb.toString();
    }
}
