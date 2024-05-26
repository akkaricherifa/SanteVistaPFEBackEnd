package com.example.santevistabackendpfe.presistence.entity;

import com.example.santevistabackendpfe.model.internal.RoleEnumeration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String email;
    private String username;
    private String mobile;
    private String gender;
    private String address;
    private String password;
    private RoleEnumeration role;

}
