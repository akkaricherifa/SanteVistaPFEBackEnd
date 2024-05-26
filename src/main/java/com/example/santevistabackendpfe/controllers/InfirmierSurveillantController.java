package com.example.santevistabackendpfe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/InfirmierSurveillant")
@PreAuthorize("hasAuthority('INFIRMIERSURVEILLANT')")

public class InfirmierSurveillantController {
}
