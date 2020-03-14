package com.zglu.generator.target.{packageName}.service;

import com.zglu.generator.target.{packageName}.dao.{className};
import com.zglu.generator.target.{packageName}.dao.{className}Dao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author {author}
 */
@Service
@AllArgsConstructor
public class {className}Service {
    private final {className}Dao {valName}Dao;

    public {className} add({className} {valName}) {
        return {valName}Dao.insert({valName});
    }

    public {className} get(long id) {
        return {valName}Dao.select(id);
    }

    public boolean put({className} {valName}) {
        return {valName}Dao.updateAll({valName});
    }

    public boolean set({className} {valName}) {
        return {valName}Dao.update({valName});
    }

    public boolean del(long id) {
        return {valName}Dao.delete(id);
    }
}
