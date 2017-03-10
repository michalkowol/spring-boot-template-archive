package com.michalkowol.hackernews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/news", produces = "application/json")
class HackerNewsController {

    private final HackerNewsService hackerNewsService;

    @Autowired
    HackerNewsController(HackerNewsService hackerNewsService) {
        this.hackerNewsService = hackerNewsService;
    }

    @RequestMapping
    HackerNews topStory() {
        return hackerNewsService.topStory();
    }
}
