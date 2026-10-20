package com.codefactory.dev_social_network.autenticacion.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefactory.dev_social_network.autenticacion.interfaces.CredencialService;

@RestController
@RequestMapping("/api/v1/auth/internal")
public class CredencialInternalController {

    private final CredencialService credencialService;

    public CredencialInternalController(CredencialService credencialService) {
        this.credencialService = credencialService;
    }

    @PostMapping("/credenciales")
    public ResponseEntity<Void> crearCredencial(@RequestBody CrearCredencialRequest request) {
        credencialService.crearCredencialLocal(request.usuarioId(), request.passwordHash());
        return ResponseEntity.status(201).build();
    }

    public record CrearCredencialRequest(UUID usuarioId, String passwordHash) {
    }
}