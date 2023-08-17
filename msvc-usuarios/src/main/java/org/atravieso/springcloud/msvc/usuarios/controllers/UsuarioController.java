package org.atravieso.springcloud.msvc.usuarios.controllers;

import org.atravieso.springcloud.msvc.usuarios.models.entity.Usuario;
import org.atravieso.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    // ResponseEntity<?> -> Si esta presente devuelve el usuario, caso contrario sin contenido (void) no devuelve nada en el cuerpo de la respuesta
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.porId(id);

        if(usuarioOptional.isPresent()) {
            // Devolver status 200 y en el cuerpo el objeto Usuario
            // ResponseEntity.ok().body(usuarioOptional.get());
            // ResponseEntity.ok(usuarioOptional.get());
            return ResponseEntity.ok(usuarioOptional.get());
        }

        // Con build generamos la respuesta con status 404 - NOT_FOUND
        return ResponseEntity.notFound().build();
    }

}
