package com.michalkowol.hackernews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
class HackerNewsService {

    private final RestTemplate restTemplate;

    @Autowired
    HackerNewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<Integer> topStories() {
        return Arrays.asList(restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/topstories.json", Integer[].class));
    }

    private HackerNews storyById(int id) {
        return restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/" + id + ".json", HackerNews.class);
    }

    HackerNews topStory() {
        int topStoryId = topStories().stream().findFirst().orElse(1);
        return storyById(topStoryId);
    }
}
