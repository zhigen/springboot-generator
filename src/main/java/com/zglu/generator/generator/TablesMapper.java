package com.zglu.generator.generator;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zglu
 */
@Component
public interface TablesMapper {

    /**
     * 查询记录
     *
     * @param tableSchema 库名
     * @return 记录
     */
    @Select("select * from tables where TABLE_SCHEMA = #{tableSchema}")
    List<Tables> findByTableSchema(@Param("tableSchema") String tableSchema);

    /**
     * 查询记录
     *
     * @param tableSchema 库名
     * @param tableNames  表名，支持多个，逗号隔开
     * @return 记录
     */
    @Select("select * from tables where TABLE_SCHEMA = #{tableSchema} and TABLE_NAME in (${tableNames})")
    List<Tables> findByTableSchemaAndTableNameIn(@Param("tableSchema") String tableSchema, @Param("tableNames") String tableNames);
}
