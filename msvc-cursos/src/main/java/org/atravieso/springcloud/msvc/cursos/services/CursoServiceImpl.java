package org.atravieso.springcloud.msvc.cursos.services;

import org.atravieso.springcloud.msvc.cursos.models.Usuario;
import org.atravieso.springcloud.msvc.cursos.models.entity.Curso;
import org.atravieso.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>)cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        return Optional.empty();
    }
}
