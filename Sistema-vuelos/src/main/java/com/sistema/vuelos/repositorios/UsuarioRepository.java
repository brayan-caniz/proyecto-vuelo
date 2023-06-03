package com.sistema.vuelos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.vuelos.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);

}
