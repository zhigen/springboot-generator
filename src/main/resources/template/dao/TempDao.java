package com.zglu.generator.target.{packageName}.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zglu.generator.generator.ReplaceUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
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

    public {className} insert({className} {valName}) {
        {valName}Mapper.insert({valName});
        return {valName};
    }

    public {className} select(long id) {
        return {valName}Mapper.select(id);
    }

    public List<{className}> selectList(String where, String orderBy, Integer offset, Integer limit) {
        String whereSql = "";
        if (StringUtils.hasText(where)) {
            whereSql += "where " + ReplaceUtils.getColumnName(where) + " ";
        }
        if (StringUtils.hasText(orderBy)) {
            whereSql += "order by " + ReplaceUtils.getColumnName(orderBy) + " ";
        }
        if (offset != null && limit != null) {
            whereSql += "limit " + limit + " offset " + offset;
        }
        return {valName}Mapper.selectList(whereSql);
    }

    public boolean updateAll({className} {valName}) {
        long rows = {valName}Mapper.updateAll({valName});
        return rows > 0;
    }

    public boolean update({className} {valName}) {
        Map<String, String> map = JSON.parseObject(JSON.toJSONString({valName}), new TypeReference<Map<String, String>>() {
        });
        List<String> setSqlList = new ArrayList<>();
        map.forEach((k, v) -> setSqlList.add(ReplaceUtils.getColumnName(k) + "='" + v + "'"));
        String setSql = String.join(",", setSqlList);
        long rows = {valName}Mapper.update(setSql, {valName}.getId());
        return rows > 0;
    }

    public boolean delete(long id) {
        long rows = {valName}Mapper.delete(id);
        return rows > 0;
    }
}
