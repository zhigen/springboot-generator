package com.zglu.generator.generator;

import lombok.Data;

/**
 * @author zglu
 */
@Data
public class Columns {
    private String tableSchema;
    private String tableName;
    private String columnName;
    private int ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String columnKey;
    private String columnComment;
}
