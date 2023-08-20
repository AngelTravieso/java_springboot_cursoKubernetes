package org.atravieso.springcloud.msvc.cursos.services;

import org.atravieso.springcloud.msvc.cursos.clients.UsuarioClientRest;
import org.atravieso.springcloud.msvc.cursos.models.Usuario;
import org.atravieso.springcloud.msvc.cursos.models.entity.Curso;
import org.atravieso.springcloud.msvc.cursos.models.entity.CursoUsuario;
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

    @Autowired
    private UsuarioClientRest usuarioClient;

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
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {

        // Buscar el curso en la BD
        Optional<Curso> o = cursoRepository.findById(cursoId);

        // Si existe el curso en la BD
        if(o.isPresent()) {
            // Verificar que el usuario exista para poder asignarlo a un curso
            Usuario usuarioMsvc = usuarioClient.detalle(usuario.getId());

            // Obtener el curso
            Curso curso = o.get();

            // Crear la relación cursoUsuario
            CursoUsuario cursoUsuario = new CursoUsuario();

            // Setear el usuarioID que llegó del msvc-usuario
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            // Agregar la relación a curso
            curso.addCursoUsuario(cursoUsuario);

            // Guardar en la BD
            cursoRepository.save(curso);

            // Retornar el usuario
            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {

        // Buscar el curso en al BD
        Optional<Curso> o = cursoRepository.findById(cursoId);

        // Si existe el curso en la BD
        if(o.isPresent()) {
            // Verificar que el usuario exista para poder asignarlo a un curso
            Usuario usuarioNuevoMsvc = usuarioClient.crear(usuario);

            Curso curso = o.get();

            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);

            return Optional.of(usuarioNuevoMsvc);
        }

        return Optional.empty();

    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        // Buscar el curso en al BD
        Optional<Curso> o = cursoRepository.findById(cursoId);

        // Si existe el curso en la BD
        if(o.isPresent()) {
            // Verificar que el usuario exista para poder asignarlo a un curso
            Usuario usuarioMsvc = usuarioClient.detalle(usuario.getId());

            Curso curso = o.get();

            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            curso.removeCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);

            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }
}
