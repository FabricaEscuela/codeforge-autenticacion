package com.codefactory.dev_social_network.autenticacion.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioLecturaEntity {

    @Id
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    protected UsuarioLecturaEntity() {
    }
}