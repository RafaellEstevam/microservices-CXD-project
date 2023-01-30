package com.spring.msuser.dataprovider.userdatabase.translator;

import com.spring.msuser.dataprovider.userdatabase.entities.UserEntity;
import com.spring.msuser.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserTranslator {

    @Autowired
    private RoleTranslator roleTranslator;

    public User execute(UserEntity userEntity){

        User user = User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(roleTranslator.toRoleCollection(userEntity.getRoles()))
                .build();

        log.info("c=UserTranslator m=execute userEntity={}, user={}", userEntity, user);
        return user;
    }

}
