package com.spring.msuser.dataprovider.userdatabase.translator;

import com.spring.msuser.dataprovider.userdatabase.entities.RoleEntity;
import com.spring.msuser.domain.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleTranslator {

    public Set<Role> toRoleCollection(Set<RoleEntity> roleEntities){
        return roleEntities.stream()
                .map(roleEntity -> toRole(roleEntity))
                .collect(Collectors.toSet());
    }

    public Role toRole(RoleEntity roleEntity){
        return  Role.builder()
                .id(roleEntity.getId())
                .roleName(roleEntity.getRoleName())
                .build();
    }

}
