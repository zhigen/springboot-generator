package com.zglu.generator.target.{packageName}.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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

    public boolean update({className} {valName}) {
        long rows = {valName}Mapper.update({valName});
        return rows > 0;
    }

    public boolean delete(long id) {
        long rows = {valName}Mapper.delete(id);
        return rows > 0;
    }
}
