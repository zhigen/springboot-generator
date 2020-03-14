package com.zglu.generator.target.{packageName}.controller;

import com.zglu.generator.target.{packageName}.dao.{className};
import com.zglu.generator.target.{packageName}.service.{className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author {author}
 */
@Api("{tableComment}")
@RestController
@AllArgsConstructor
public class {className}Controller {
    private final {className}Service {valName}Service;

    @PostMapping("/{tableNameMid}")
    @ApiOperation("增")
    public {className} add(@RequestBody {className} {valName}) {
        return {valName}Service.add({valName});
    }

    @GetMapping("/{tableNameMid}/{id}")
    @ApiOperation("查")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "id", required = true),
    })
    public {className} get(@PathVariable long id) {
        return {valName}Service.get(id);
    }

    @GetMapping("/{tableNameMid}")
    @ApiOperation("查列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "where", value = "条件，除属性驼峰外全小写", defaultValue = "id > 10"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderBy", value = "排序，除属性驼峰外全小写", defaultValue = "id desc"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "offset", value = "偏移量", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "limit", value = "行数", defaultValue = "3"),
    })
    public List<{className}> get(String where, String orderBy, Integer offset, Integer limit) {
        return {valName}Service.get(where, orderBy, offset, limit);
    }

    @PutMapping("/{tableNameMid}")
    @ApiOperation("覆盖改")
    public boolean put(@RequestBody {className} {valName}) {
        return {valName}Service.put({valName});
    }

    @PatchMapping("/{tableNameMid}")
    @ApiOperation("改")
    public boolean set(@RequestBody {className} {valName}) {
        return {valName}Service.set({valName});
    }

    @DeleteMapping("/{tableNameMid}/{id}")
    @ApiOperation("删")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "id", required = true),
    })
    public boolean del(@PathVariable long id) {
        return {valName}Service.del(id);
    }
}
