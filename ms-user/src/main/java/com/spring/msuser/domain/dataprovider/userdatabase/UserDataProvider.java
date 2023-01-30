package com.spring.msuser.domain.dataprovider.userdatabase;

import com.spring.msuser.dataprovider.userdatabase.entities.UserEntity;
import com.spring.msuser.domain.model.User;

public interface UserDataProvider {

    User findById(Long id);
    User findByEmail(String email);
}
