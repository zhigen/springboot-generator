package com.zglu.generator.target.{packageName}.service;

import com.zglu.generator.Page;
import com.zglu.generator.target.{packageName}.dao.{className};
import com.zglu.generator.target.{packageName}.dao.{className}Dao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author {author}
 */
@Service
@AllArgsConstructor
public class {className}Service {
    private final {className}Dao {valName}Dao;

    public {className} add({className} {valName}) {
        return {valName}Dao.save({valName});
    }

    public {className} get(long id) {
        return {valName}Dao.findById(id);
    }

    public List<{className}> list(String q, String order, Integer offset, Integer limit) {
        return {valName}Dao.findAll(q, order, offset, limit);
    }

    public Page<{className}> page(String q, String order, Integer number, Integer size) {
        long totalElements = {valName}Dao.count(q);
        Page<{className}> page = new Page<>(totalElements, number, size);
        List<{className}> content = {valName}Dao.findAll(q, order, page.getOffset(), size);
        page.setContent(content);
        return page;
    }

    public {className} put({className} {valName}) {
        return {valName}Dao.put({valName});
    }

    public {className} set({className} {valName}) {
        return {valName}Dao.update({valName});
    }

    public int remove(long id) {
        return {valName}Dao.deleteById(id);
    }
}
