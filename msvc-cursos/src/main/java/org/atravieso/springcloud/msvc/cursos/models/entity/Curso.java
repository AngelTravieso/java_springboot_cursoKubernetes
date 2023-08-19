package org.atravieso.springcloud.msvc.cursos.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.atravieso.springcloud.msvc.cursos.models.Usuario;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    // Un solo curso puede tener muchos cursosUsuarios
    // CascadeType.All -> Cada vez que se crea/elimina un curso con usuarios primero guarda el curso y en cascada guarda/elimina los usuarios
    // orphanRemoval (true) -> Que no queden volando cursoId que no están asignados a ningún curso en particular, todos los ID de curso que sean nulo que los elimine
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id") // FK
    private List<CursoUsuario> cursoUsuarios;

    // Para indicar que no está mapeado a persistencia, no está mapeado a una tabla
    @Transient
    private List<Usuario> usuarios;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addCursoUsuario(CursoUsuario cursoUsuario) {
        cursoUsuarios.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario) {
        cursoUsuarios.remove(cursoUsuario);
    }

    public List<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public void setCursoUsuarios(List<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
