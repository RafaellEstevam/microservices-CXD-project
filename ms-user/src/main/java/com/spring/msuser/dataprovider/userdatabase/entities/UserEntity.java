package com.spring.msuser.dataprovider.userdatabase.entities;


import com.spring.msuser.dataprovider.userdatabase.translator.RoleEntityTranslator;
import com.spring.msuser.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_user")
public class UserEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();


    public UserEntity(Builder builder){
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;;
        this.roles = builder.roles;
    }

    public static class Builder {

        private String name;
        private String email;
        private String password;
        private Set<RoleEntity> roles = new HashSet<>();

        public Builder builder(){
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder roles(Set<Role> roles){
            this.roles = new RoleEntityTranslator().toRoleEntityCollection(roles);
            return this;
        }

        public UserEntity build(){
            return new UserEntity(this);
        }

    }
}
