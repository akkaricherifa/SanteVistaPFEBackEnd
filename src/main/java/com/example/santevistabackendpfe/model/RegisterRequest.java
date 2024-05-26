package com.example.santevistabackendpfe.model;

import com.example.santevistabackendpfe.exception.ApplicationException;
import com.example.santevistabackendpfe.model.internal.RoleEnumeration;
import org.springframework.util.StringUtils;

public record RegisterRequest(String username, String email, String password, String gender, String address, String mobile,  RoleEnumeration role,
                              String MedecinJeuneCardNumber,
                              String InfirmierKineCardNumber,
                              String InfirmierSoignantCardNumber,
                              String InfirmierSurveillantCardNumber,
                              String SecretaireCardNumber) {
    public RegisterRequest {
        if (!StringUtils.hasText(username)) {
            throw new ApplicationException("Username is required");
        }
        if (!StringUtils.hasText(email)) {
            throw new ApplicationException("Email is required");
        }
        if (!StringUtils.hasText(password)) {
            throw new ApplicationException("Password is required");
        }

    }
}
