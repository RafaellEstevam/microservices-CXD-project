package com.spring.msuser.domain.usecase;

import com.spring.msuser.domain.dataprovider.userdatabase.UserDataProvider;
import com.spring.msuser.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveUserUseCase {

    private final UserDataProvider userDataProvider;

    public User findById(Long id){
        return userDataProvider.findById(id);
    }

    public User findByEmail(String email){
        return userDataProvider.findByEmail(email);
    }

}
