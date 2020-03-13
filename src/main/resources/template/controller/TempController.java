package com.zglu.generator.target.{packageName}.controller;

import com.zglu.generator.target.{packageName}.dao.{className};
import com.zglu.generator.target.{packageName}.service.{className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{tableNameMid}")
    @ApiOperation("改")
    public boolean put(@RequestBody {className} {valName}) {
        return {valName}Service.put({valName});
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
