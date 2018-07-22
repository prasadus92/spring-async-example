package com.welt.data.service;

import com.welt.data.dto.PostDto;
import com.welt.data.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component("userDataService")
public class UserDataService {

    private final Logger log = LoggerFactory.getLogger(UserDataService.class);

    private static final String externalDataApiBaseUrl = "http://jsonplaceholder.typicode.com";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async("asyncExecutor")
    public CompletableFuture<UserDto> getUserAsync(Integer userId) throws RestClientException {

        log.info("Fetching user asynchronously");
        log.debug(Thread.currentThread().getName() + String.valueOf(Thread.currentThread().getId()));
        UserDto user = restTemplate.getForObject(externalDataApiBaseUrl + "/users/" + userId, UserDto.class);
        return CompletableFuture.completedFuture(user);
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<PostDto>> getPostsAsync(Integer userId) {

        log.info("Fetching user posts asynchronously");
        log.debug(Thread.currentThread().getName() + String.valueOf(Thread.currentThread().getId()));
        ResponseEntity<List<PostDto>> rateResponse =
                restTemplate.exchange(externalDataApiBaseUrl + "/posts?userId=" + userId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDto>>() {
                        });
        List<PostDto> posts = rateResponse.getBody();
        return CompletableFuture.completedFuture(posts);
    }
}
