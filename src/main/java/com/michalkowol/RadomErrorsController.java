package com.michalkowol;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/errors", produces = "application/json")
class RadomErrorsController {

    @RequestMapping
    Map<String, String> randomError() {
        int random = new Random().nextInt(3);
        if (random == 0) {
            throw new IllegalStateException("Random error");
        } else if (random == 1) {
            throw new Errors.NotFoundException("Not found");
        }
        return ImmutableMap.of("health", "ok");
    }
}
