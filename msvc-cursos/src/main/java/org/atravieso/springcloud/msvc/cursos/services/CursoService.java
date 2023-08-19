package org.atravieso.springcloud.msvc.cursos.services;

import org.atravieso.springcloud.msvc.cursos.models.Usuario;
import org.atravieso.springcloud.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    // Recibe el usuario a asignar y el ID del curso
    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    // Esto es para crear un usuario que no existe en el msvc-usuarios, desde cursos se envía un usuario para que lo cree e inserte en MySQL
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);

    // Esto quita al usuario de un curso en particular, esto NO lo elimina de la BD usuarios del msvc-usuarios, esto permite que el usuario lo podamos asignar a otro curso
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

}
