package com.spring.msuser.userinterface.translator;

import com.spring.msuser.domain.model.User;
import com.spring.msuser.userinterface.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserResponseTranslator {

    public UserResponse execute(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
