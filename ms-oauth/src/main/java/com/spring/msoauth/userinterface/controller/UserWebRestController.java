package com.spring.msoauth.userinterface.controller;

import com.spring.msoauth.dataprovider.msuser.feign.response.UserResponse;
import com.spring.msoauth.domain.usecase.RetrieveUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserWebRestController {

    private final RetrieveUserUseCase retrieveUserUseCase;

    @GetMapping
    public ResponseEntity<UserResponse>findByEmail(@RequestParam String email){
            UserResponse userResponse = retrieveUserUseCase.execute(email);
            return ResponseEntity.ok(userResponse);
    }
}
