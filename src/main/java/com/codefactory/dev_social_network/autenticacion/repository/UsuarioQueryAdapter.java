package com.codefactory.dev_social_network.autenticacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.codefactory.dev_social_network.autenticacion.dto.UsuarioDTO;
import com.codefactory.dev_social_network.autenticacion.interfaces.UsuarioQueryService;

import jakarta.persistence.EntityManager;

@Component
public class UsuarioQueryAdapter implements UsuarioQueryService {

    private final EntityManager entityManager;

    public UsuarioQueryAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        List<UsuarioDTO> resultados = entityManager.createQuery(
                        "select new com.codefactory.dev_social_network.autenticacion.dto.UsuarioDTO(u.id, u.email) "
                      + "from UsuarioLecturaEntity u where u.email = :email",
                        UsuarioDTO.class)
                .setParameter("email", email)
                .getResultList();
        return resultados.stream().findFirst();
    }
}