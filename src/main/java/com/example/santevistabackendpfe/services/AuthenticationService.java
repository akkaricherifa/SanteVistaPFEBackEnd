package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.exception.ApplicationException;
import com.example.santevistabackendpfe.model.RegisterRequest;
import com.example.santevistabackendpfe.model.internal.RoleEnumeration;
import com.example.santevistabackendpfe.model.request.LoginRequest;
import com.example.santevistabackendpfe.model.response.LoginResponse;
import com.example.santevistabackendpfe.presistence.entity.*;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import com.example.santevistabackendpfe.security.JWTService;
import com.example.santevistabackendpfe.utils.ApplicationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.santevistabackendpfe.model.internal.RoleEnumeration.*;

@Component
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(loginRequest.username());
        if(optionalUserEntity.isEmpty()){
            throw new ApplicationException("User not found with username: " + loginRequest.username());
        }
        UserEntity userEntity = optionalUserEntity.get();
        boolean PasswordMatches = passwordEncoder.matches(loginRequest.password(), userEntity.getPassword());
        if(!PasswordMatches){
            throw new ApplicationException("Invalid password");
        }
        String generatedToken = jwtService.generateToken(userEntity.getUsername());
        ApplicationUtils.setHeaderValue(HttpHeaders.AUTHORIZATION, generatedToken);
        return new LoginResponse(userEntity.getEmail(), userEntity.getGender(), userEntity.getRole().name());
    }


    public void register(RegisterRequest registerRequest) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(registerRequest.email());
        if (existingUser.isPresent()) {
            throw new ApplicationException("User with email " + registerRequest.email() + " already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerRequest.username());
        user.setGender(registerRequest.gender());
        user.setAddress(registerRequest.address());
        user.setMobile(registerRequest.mobile());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());
        switch (registerRequest.role()) {
            case UserEntity:
                user.setRole(SECRETAIRE);
                userRepository.save(user);
                break;

            case MEDECINJEUNE:
                MedecinJeune medecinJeune = new MedecinJeune();
                medecinJeune.setUsername(registerRequest.username());
                medecinJeune.setGender(registerRequest.gender());
                medecinJeune.setAddress(registerRequest.address());
                medecinJeune.setMobile(registerRequest.mobile());
                medecinJeune.setPassword(passwordEncoder.encode(registerRequest.password()));
                medecinJeune.setEmail(registerRequest.email());
                medecinJeune.setRole(MEDECINJEUNE);
                medecinJeune.setMedecinJeuneCardNumber(registerRequest.MedecinJeuneCardNumber());
                userRepository.save(medecinJeune);
                break;

            case MEDECINSENIOR:
                MedecinSenior medecinSenior = new MedecinSenior();
                medecinSenior.setUsername(registerRequest.username());
                medecinSenior.setGender(registerRequest.gender());
                medecinSenior.setAddress(registerRequest.address());
                medecinSenior.setMobile(registerRequest.mobile());
                medecinSenior.setPassword(passwordEncoder.encode(registerRequest.password()));
                medecinSenior.setEmail(registerRequest.email());
                medecinSenior.setRole(MEDECINSENIOR);
                userRepository.save(medecinSenior);
                break;


            case INFIRMIERKINE:
                InfirmierKine infirmierKine = new InfirmierKine();
                infirmierKine.setUsername(registerRequest.username());
                infirmierKine.setGender(registerRequest.gender());
                infirmierKine.setAddress(registerRequest.address());
                infirmierKine.setMobile(registerRequest.mobile());
                infirmierKine.setRole(INFIRMIERKINE);
                infirmierKine.setPassword(passwordEncoder.encode(registerRequest.password()));
                infirmierKine.setEmail(registerRequest.email());
                infirmierKine.setInfirmierKineCardNumber(registerRequest.InfirmierKineCardNumber());
                userRepository.save(infirmierKine);
                break;


            case INFIRMIERSOIGNANT:
                InfirmierSoignant infirmierSoignant = new InfirmierSoignant();
                infirmierSoignant.setUsername(registerRequest.username());
                infirmierSoignant.setGender(registerRequest.gender());
                infirmierSoignant.setAddress(registerRequest.address());
                infirmierSoignant.setMobile(registerRequest.mobile());
                infirmierSoignant.setRole(INFIRMIERSOIGNANT);
                infirmierSoignant.setPassword(passwordEncoder.encode(registerRequest.password()));
                infirmierSoignant.setEmail(registerRequest.email());
                infirmierSoignant.setInfirmierSoignantCardNumber(registerRequest.InfirmierSoignantCardNumber());
                userRepository.save(infirmierSoignant);
                break;


            case INFIRMIERSURVEILLANT:
                InfirmierSurveillant infirmierSurveillant = new InfirmierSurveillant();
                infirmierSurveillant.setUsername(registerRequest.username());
                infirmierSurveillant.setGender(registerRequest.gender());
                infirmierSurveillant.setAddress(registerRequest.address());
                infirmierSurveillant.setMobile(registerRequest.mobile());
                infirmierSurveillant.setRole(INFIRMIERSURVEILLANT);
                infirmierSurveillant.setPassword(passwordEncoder.encode(registerRequest.password()));
                infirmierSurveillant.setEmail(registerRequest.email());
                infirmierSurveillant.setInfirmierSurveillantCardNumber(registerRequest.InfirmierSurveillantCardNumber());
                userRepository.save(infirmierSurveillant);
                break;


            case SECRETAIRE:
                Secretaire secretaire = new Secretaire();
                secretaire.setUsername(registerRequest.username());
                secretaire.setGender(registerRequest.gender());
                secretaire.setAddress(registerRequest.address());
                secretaire.setMobile(registerRequest.mobile());
                secretaire.setRole(SECRETAIRE);
                secretaire.setPassword(passwordEncoder.encode(registerRequest.password()));
                secretaire.setEmail(registerRequest.email());
                secretaire.setSecretaireCardNumber(registerRequest.SecretaireCardNumber());
                userRepository.save(secretaire);
                break;
            default:
                throw new ApplicationException("Invalid role specified");


        }





    }
}
