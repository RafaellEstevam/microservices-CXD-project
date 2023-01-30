package com.spring.msoauth.domain.usecase;

import com.spring.msoauth.dataprovider.msuser.feign.response.UserResponse;
import com.spring.msoauth.domain.dataprovider.msuser.MsUserDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveUserUseCase {

    private final MsUserDataProvider msUserDataProvider;

    public UserResponse execute(String email){
        return msUserDataProvider.loadUserByUsername(email);
    }
}
