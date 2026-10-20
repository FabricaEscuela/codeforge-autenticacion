package com.codefactory.dev_social_network.autenticacion.interfaces;

import java.util.Optional;

import com.codefactory.dev_social_network.autenticacion.dto.UsuarioDTO;

public interface UsuarioQueryService {

    Optional<UsuarioDTO> buscarPorEmail(String email);

}