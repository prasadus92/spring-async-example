package com.welt.data.service.impl;

import com.welt.data.dto.PostDto;
import com.welt.data.dto.UserDataDto;
import com.welt.data.dto.UserDto;
import com.welt.data.service.UserDataService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component("userDataService")
public class UserDataServiceImpl implements UserDataService {

    private static final String externalDataApiBaseUrl = "http://jsonplaceholder.typicode.com";

    private final WebClient externalDataApiClient = WebClient.builder().baseUrl(externalDataApiBaseUrl).build();

    @Override
    public Mono<UserDataDto> getUserData(Integer userId) {

        return Mono.zip(getUser(userId), getPosts(userId), (u, p) -> new UserDataDto(u, p));

    }

    private Mono<UserDto> getUser(Integer userId) {

        return externalDataApiClient.get()
                .uri("/users/" + userId)
                .retrieve()
                .bodyToMono(UserDto.class);

    }

    private Mono<List<PostDto>> getPosts(Integer userId) {

        return externalDataApiClient.get()
                .uri("/posts?userId=" + userId)
                .retrieve()
                .bodyToFlux(PostDto.class)
                .collectList();

    }
}
