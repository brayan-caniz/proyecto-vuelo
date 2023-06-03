package com.sistema.vuelos.servicios;

import java.util.Set;

import com.sistema.vuelos.modelo.Usuario;
import com.sistema.vuelos.modelo.UsuarioRol;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);
}
