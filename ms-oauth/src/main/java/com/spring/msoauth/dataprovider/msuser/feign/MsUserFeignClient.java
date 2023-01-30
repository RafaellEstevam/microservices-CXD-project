package com.spring.msoauth.dataprovider.msuser.feign;

import com.spring.msoauth.dataprovider.msuser.feign.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "ms-user", url = "localhost:8082", path = "/user")
public interface MsUserFeignClient {

    @GetMapping
    ResponseEntity<UserResponse> findByEmail(@RequestParam String email);
}
