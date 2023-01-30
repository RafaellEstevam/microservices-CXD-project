package com.spring.msoauth.dataprovider.msuser;


import com.spring.msoauth.dataprovider.msuser.feign.MsUserFeignClient;
import com.spring.msoauth.dataprovider.msuser.feign.response.UserResponse;
import com.spring.msoauth.domain.dataprovider.msuser.MsUserDataProvider;
import com.spring.msoauth.domain.exception.UserNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MsUserDataProviderImpl implements MsUserDataProvider, UserDetailsService {

   private final MsUserFeignClient msUserFeignClient;

    @Override
    public UserResponse loadUserByUsername(String email) {
        ResponseEntity<UserResponse> responseEntity = null;
        try {
            responseEntity = msUserFeignClient.findByEmail(email);
        }catch (FeignException.NotFound e){
            throw  new UserNotFoundException("user not found");
        }

        UserResponse userResponse = responseEntity.getBody();
        log.info("c=HrUserDataProviderImpl m= findByEmail email={}, userResponse={}", email, userResponse);
        return userResponse;
    }
}
