package com.welt.data.controller;

import com.welt.data.dto.PostDto;
import com.welt.data.dto.UserDataDto;
import com.welt.data.dto.UserDto;
import com.welt.data.service.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
public class UserDataController {

    private final Logger log = LoggerFactory.getLogger(UserDataController.class);

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/users/{id}/data")
    @ResponseBody
    public UserDataDto getUserData(@PathVariable Integer id) throws RestClientException, ExecutionException, InterruptedException {

        log.info("New request to fetch user data for userId - {}", id);
        CompletableFuture<UserDto> user = userDataService.getUserAsync(id);
        CompletableFuture<List<PostDto>> posts = userDataService.getPostsAsync(id);
        CompletableFuture.allOf(user, posts).join();
        return new UserDataDto(user.get(), posts.get());
    }
}
