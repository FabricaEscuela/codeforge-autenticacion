package com.codefactory.dev_social_network.autenticacion.seguridad;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InternalTokenFilter extends OncePerRequestFilter {

    private static final String PREFIJO = "/api/v1/auth/internal/";
    private static final String CABECERA = "X-Internal-Token";

    private final byte[] tokenEsperado;

    public InternalTokenFilter(@Value("${app.internal.token:}") String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Falta app.internal.token; el endpoint interno quedaria sin proteccion");
        }
        this.tokenEsperado = token.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(PREFIJO);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String recibido = request.getHeader(CABECERA);
        boolean valido = recibido != null
                && MessageDigest.isEqual(recibido.getBytes(StandardCharsets.UTF_8), tokenEsperado);
        if (!valido) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }
}