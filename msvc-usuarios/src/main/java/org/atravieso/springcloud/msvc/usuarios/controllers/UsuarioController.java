package org.atravieso.springcloud.msvc.usuarios.controllers;

import org.atravieso.springcloud.msvc.usuarios.models.entity.Usuario;
import org.atravieso.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED) // -> Retornar un 201, ya que por defecto regresa un 200
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody() Usuario usuario, @PathVariable Long id) {
        Optional<Usuario> usuarioBD = usuarioService.porId(id);
        if(usuarioBD.isPresent()) {
            Usuario usuarioActualizado = usuarioBD.get();
            usuarioActualizado.setNombre(usuario.getNombre());
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioActualizado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> usuarioBD = usuarioService.porId(id);
        if(usuarioBD.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
