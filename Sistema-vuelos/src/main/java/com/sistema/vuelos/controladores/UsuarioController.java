package com.sistema.vuelos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sistema.vuelos.modelo.Rol;
import com.sistema.vuelos.modelo.Usuario;
import com.sistema.vuelos.modelo.UsuarioRol;
import com.sistema.vuelos.servicios.UsuarioService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        // Endpoint para guardar un nuevo usuario
        usuario.setPerfil("default.png");

        // Crear un rol "NORMAL"
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        // Crear una relación UsuarioRol con el usuario y el rol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        // Agregar la relación UsuarioRol al conjunto de roles del usuario
        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        usuarioRoles.add(usuarioRol);

        // Guardar el usuario con sus roles en la base de datos
        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        // Endpoint para obtener un usuario por su nombre de usuario
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        // Endpoint para eliminar un usuario por su ID
        usuarioService.eliminarUsuario(usuarioId);
    }
}

