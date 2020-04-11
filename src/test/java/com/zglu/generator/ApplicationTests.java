package com.zglu.generator;

import com.zglu.generator.generator.GeneratorService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author zglu
 */
@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {
    @Autowired
    private GeneratorService service;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(service, "service must not be null!");
        service.generate();
    }

}
