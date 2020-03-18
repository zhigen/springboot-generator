package com.zglu.generator.generator;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class GeneratorController {

    private final GeneratorService generatorService;

    @PostMapping
    @ApiOperation("生成代码")
    public void generate() {
        generatorService.generate();
    }

}
