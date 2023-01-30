package com.spring.msuser.dataprovider.userdatabase.translator;

import com.spring.msuser.dataprovider.userdatabase.entities.RoleEntity;
import com.spring.msuser.domain.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleEntityTranslator {

    public Set<RoleEntity> toRoleEntityCollection(Set<Role> roles){
        return roles.stream()
                .map( role -> toRoleEntity(role))
                .collect(Collectors.toSet());
    }


    public RoleEntity toRoleEntity(Role role){
        return RoleEntity.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }

}
