package com.welt.data.controller;

import com.welt.data.dto.UserDataDto;
import com.welt.data.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserDataRestController {

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/users/{id}/data")
    @ResponseBody
    public Mono<UserDataDto> getUserData(@PathVariable Integer id) {

        return userDataService.getUserData(id);

    }

}
