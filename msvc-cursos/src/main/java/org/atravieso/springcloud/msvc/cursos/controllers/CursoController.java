package org.atravieso.springcloud.msvc.cursos.controllers;

import feign.FeignException;
import jakarta.validation.Valid;
import org.atravieso.springcloud.msvc.cursos.models.Usuario;
import org.atravieso.springcloud.msvc.cursos.models.entity.Curso;
import org.atravieso.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        // Sin objetos de usuarios
        // Optional<Curso> curso = cursoService.porId(id);

        // Obtener los cursos con los usuarios (objetos completos)
        Optional<Curso> curso = cursoService.porIdconUsuarios(id);

        if(curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        // Verificar si hay errores
        if(result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
        // Verificar si hay errores
        if(result.hasErrors()) {
            return validar(result);
        }

        Optional<Curso> cursoDB = cursoService.porId(id);
        if(cursoDB.isPresent()) {
            Curso cursoActualizado = cursoDB.get();
            cursoActualizado.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoActualizado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> cursoDB = cursoService.porId(id);
        if(cursoDB.isPresent()) {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> o;

        // Intentar comunicarnos al msvc-usuarios
        try {
            // Asignar usuario al curso
            o = cursoService.asignarUsuario(usuario, cursoId);

        } catch (FeignException e) {
            // Si ocurre algún error con el microservicio (comunicación, etc)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("mensaje", String.format("No se puedo crear el usuario o error en la comunicación: %s", e.getMessage()))
            );
        }

        // Si no hubo problema y el usuario existe
        if(o.isPresent()) {
            // Retornar el objeto usuario que asignamos
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        // Si no existe devolver un 404
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> o;

        // Intentar comunicarnos al msvc-usuarios
        try {
            // Asignar usuario al curso
            o = cursoService.crearUsuario(usuario, cursoId);

        } catch (FeignException e) {
            // Si ocurre algún error con el microservicio (comunicación, etc)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("mensaje", String.format("No se pudo crear el usuario o error en la comunicación: %s", e.getMessage()))
            );
        }

        // Si no hubo problema y el usuario existe
        if(o.isPresent()) {
            // Retornar el objeto usuario que asignamos
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        // Si no existe devolver un 404
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> o;

        // Intentar comunicarnos al msvc-usuarios
        try {
            // Asignar usuario al curso
            o = cursoService.eliminarUsuario(usuario, cursoId);

        } catch (FeignException e) {
            // Si ocurre algún error con el microservicio (comunicación, etc)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("mensaje", String.format("No se puedo eliminar el usuario o error en la comunicación: %s", e.getMessage()))
            );
        }

        // Si no hubo problema y el usuario existe
        if(o.isPresent()) {
            // Retornar el objeto usuario que asignamos
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        // Si no existe devolver un 404
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/eliminar-usuario/{usuarioId}")
    public ResponseEntity<?> eliminarCursoUsuarioPorId(@PathVariable Long usuarioId) {
        cursoService.eliminarCursoUsuarioPorId(usuarioId);
        return ResponseEntity.noContent().build();
    }

    // Método para validar el body de la petición
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), String.format("El campo %s %s", err.getField(), err.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errores);
    }

}
