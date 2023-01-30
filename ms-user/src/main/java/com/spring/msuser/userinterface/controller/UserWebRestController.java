package com.spring.msuser.userinterface.controller;

import com.spring.msuser.domain.model.User;
import com.spring.msuser.domain.usecase.RetrieveUserUseCase;
import com.spring.msuser.exception.UserNotFoundException;
import com.spring.msuser.userinterface.dto.UserResponse;
import com.spring.msuser.userinterface.translator.UserResponseTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserWebRestController {

    private final UserResponseTranslator userResponseTranslator;
    private final RetrieveUserUseCase retrieveUserUseCase;


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){

        try {
            User user = retrieveUserUseCase.findById(id);
            UserResponse userResponse = userResponseTranslator.execute(user);

            log.info("c=UserController m=findById user={}, userResponse={}", user, userResponse);
            return ResponseEntity.ok(userResponse);
        }catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email){

        try {
            User user = retrieveUserUseCase.findByEmail(email);
            UserResponse userResponse = userResponseTranslator.execute(user);

            log.info("c=UserController m=findByEmail user={}, userResponse={}", user, userResponse);
            return ResponseEntity.ok(userResponse);
        }catch (UserNotFoundException e){
            log.error("c=UserController m=findByEmail e={}, message={}", e, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
