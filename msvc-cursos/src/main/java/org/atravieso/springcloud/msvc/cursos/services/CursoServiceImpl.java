package org.atravieso.springcloud.msvc.cursos.services;

import org.atravieso.springcloud.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService {
    @Override
    public List<Curso> listar() {
        return null;
    }

    @Override
    public Optional<Curso> porId(Long id) {
        return Optional.empty();
    }

    @Override
    public Curso guardar(Curso curso) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
