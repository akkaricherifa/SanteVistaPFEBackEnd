package com.example.santevistabackendpfe.model.internal;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnumeration implements GrantedAuthority {
   MEDECINJEUNE, MEDECINSENIOR, INFIRMIERKINE, INFIRMIERSOIGNANT, INFIRMIERSURVEILLANT, SECRETAIRE, UserEntity;

    @Override
    public String getAuthority() {
        return this.name();
    }

}
