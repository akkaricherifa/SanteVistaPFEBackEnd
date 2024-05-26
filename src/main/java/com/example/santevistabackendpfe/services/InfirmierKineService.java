package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.repository.InfirmierKineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfirmierKineService {
    private final InfirmierKineRepository infirmierKineRepository;
}
