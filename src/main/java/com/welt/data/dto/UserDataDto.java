package com.welt.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDataDto {

    public UserDataDto(UserDto user, List<PostDto> posts) {
        this.user = user;
        this.posts = posts;
    }

    @JsonProperty(value = "user")
    private UserDto user;

    @JsonProperty(value = "posts")
    private List<PostDto> posts;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
}
