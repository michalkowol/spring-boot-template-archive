package com.michalkowol.hackernews;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/news", produces = "application/json")
class HackerNewsController {

    private final HackerNewsService hackerNewsService;

    @Inject
    HackerNewsController(HackerNewsService hackerNewsService) {
        this.hackerNewsService = hackerNewsService;
    }

    @RequestMapping
    HackerNews topStory() {
        return hackerNewsService.topStory();
    }
}
