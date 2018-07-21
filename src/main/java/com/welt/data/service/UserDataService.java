package com.welt.data.service;

import com.welt.data.dto.UserDataDto;
import reactor.core.publisher.Mono;

public interface UserDataService {

    Mono<UserDataDto> getUserData(Integer userId);

}
