package com.zglu.generator.target.{packageName}.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author {author}
 */
@Component
public interface {className}Mapper {

    /**
     * C
     *
     * @param {valName} 记录
     * @return 影响行数
     */
    @Insert("insert into {tableName}({columnString}) " +
            "values({fieldString4Mapper})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = long.class)
    long insert({className} {valName});

    /**
     * R
     *
     * @param id 记录id
     * @return 记录
     */
    @Select("select * from {tableName} where id = #{id}")
    {className} select(@Param("id") long id);

    /**
     * U
     *
     * @param {valName} 记录
     * @return 影响行数
     */
    @Update("update {tableName} set " +
            "{updateString4Mapper} " +
            "where id=#{id}")
    long updateAll({className} {valName});

    /**
     * U
     *
     * @param setSql set语句
     * @param id     记录id
     * @return 影响行数
     */
    @Update("update {tableName} set " +
            "${setSql} " +
            "where id=#{id}")
    long update(@Param("setSql") String setSql, @Param("id") long id);

    /**
     * D
     *
     * @param id 记录id
     * @return 影响行数
     */
    @Delete("delete from {tableName} where id=#{id}")
    long delete(@Param("id") long id);
}