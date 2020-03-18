package com.zglu.generator.target.{packageName}.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zglu.generator.generator.ReplaceUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author {author}
 */
@Component
@AllArgsConstructor
public class {className}Dao {
    private final {className}Mapper {valName}Mapper;

    public {className} save({className} {valName}) {
        {valName}Mapper.save({valName});
        return {valName};
    }

    public {className} findById(long id) {
        return {valName}Mapper.findById(id);
    }

    public List<{className}> findAll(String q, String order, Integer offset, Integer limit) {
        String searchSql = "";
        if (StringUtils.hasText(q)) {
            searchSql += "WHERE " + ReplaceUtils.getColumnName(q);
        }
        if (StringUtils.hasText(order)) {
            searchSql += " ORDER BY " + ReplaceUtils.getColumnName(order);
        }
        if (offset != null && limit != null) {
            searchSql += " LIMIT " + limit + " OFFSET " + offset;
        }
        return {valName}Mapper.findAll(searchSql);
    }

    public long count(String q) {
        String searchSql = "";
        if (StringUtils.hasText(q)) {
            searchSql += "WHERE " + ReplaceUtils.getColumnName(q);
        }
        return {valName}Mapper.count(searchSql);
    }

    public {className} put({className} {valName}) {
        Assert.notNull({valName}.getId(), "id must not be null!");
        Assert.isTrue({valName}.getId() != 0L, "id must not be zero!");
        if ({valName}Mapper.updateAll({valName}) <= 0) {
            {valName}Mapper.save({valName});
        }
        return {valName};
    }

    public {className} update({className} {valName}) {
        {valName}Mapper.update({valName});
        return {valName};
    }

    public int deleteById(long id) {
        return {valName}Mapper.deleteById(id);
    }
}
