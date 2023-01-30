package com.spring.msoauth.domain.dataprovider.msuser;

import com.spring.msoauth.dataprovider.msuser.feign.response.UserResponse;

public interface MsUserDataProvider {

    UserResponse loadUserByUsername(String email);
}
