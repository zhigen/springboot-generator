package com.zglu.generator.{0}.dao;

import com.zglu.mybatis.entity.{2};
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author zglu
 */
@Component
public interface {2}Mapper {

    /**
     * 查询记录
     *
     * @param id 记录id
     * @return 记录
     */
    @Select("select * from {1} where id = #{id}")
    {2} findById(@Param("id") long id);
/**
 * 查询记录
 *
 * @param id 记录id
 * @return 记录
 */
@Select("select * from {1} where id = #{id}")
    {2} findById(@Param("id") long id);

/**
 * 查询记录
 *
 * @param id 记录id
 * @return 记录
 */
@Select("select * from {1} where id = #{id}")
    {2} findById(@Param("id") long id);

/**
 * 查询记录
 *
 * @param id 记录id
 * @return 记录
 */
@Select("select * from {1} where id = #{id}")
    {2} findById(@Param("id") long id);


}
