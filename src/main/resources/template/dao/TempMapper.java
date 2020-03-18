package com.zglu.generator.target.{packageName}.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Insert("INSERT INTO {tableName}({columnString}) " +
            "VALUES({fieldString4Mapper})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = long.class)
    int save({className} {valName});

    /**
     * R
     *
     * @param id 记录id
     * @return 记录
     */
    @Select("SELECT * FROM {tableName} WHERE id = #{id}")
    {className} findById(@Param("id") long id);

    /**
     * R
     *
     * @param searchSql 搜索语句
     * @return 记录集
     */
    @Select("SELECT * FROM {tableName} ${searchSql}")
    List<{className}> findAll(@Param("searchSql") String searchSql);

    /**
     * count
     *
     * @param searchSql 搜索语句
     * @return 记录总数
     */
    @Select("SELECT COUNT(id) FROM {tableName} ${searchSql}")
    long count(@Param("searchSql") String searchSql);

    /**
     * U
     *
     * @param {valName} 记录
     * @return 影响行数
     */
    @Update("UPDATE {tableName} SET " +
            "{mapperUpdateAllString} " +
            "WHERE id = #{id}")
    int updateAll({className} {valName});

    /**
     * U
     *
     * @param {valName} 记录
     * @return 影响行数
     */
    @Update("UPDATE {tableName} SET " +
            "{mapperUpdateString} " +
            "WHERE id = #{id}")
    int update({className} {valName});

    /**
     * D
     *
     * @param id 记录id
     * @return 影响行数
     */
    @Delete("DELETE FROM {tableName} WHERE id = #{id}")
    int deleteById(@Param("id") long id);
}