package com.codefactory.dev_social_network.autenticacion.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.persistence.Table;

class CredencialEntityTest {

    @Test
    @DisplayName("se mapea a la tabla real 'credenciales' definida en schema.sql")
    void seMapeaALaTablaReal() {
        Table tabla = CredencialEntity.class.getAnnotation(Table.class);

        assertThat(tabla).isNotNull();
        assertThat(tabla.name()).isEqualTo("credenciales");
    }

    @Test
    @DisplayName("una credencial nueva empieza sin intentos fallidos")
    void empiezaSinIntentosFallidos() {
        CredencialEntity credencial = new CredencialEntity(UUID.randomUUID(), "LOCAL", "hash", null);

        assertThat(credencial.getIntentosFallidos()).isZero();
        assertThat(credencial.getTipo()).isEqualTo("LOCAL");
    }
}
