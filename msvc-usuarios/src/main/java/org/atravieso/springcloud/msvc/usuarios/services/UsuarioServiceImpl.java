package org.atravieso.springcloud.msvc.usuarios.services;

import org.atravieso.springcloud.msvc.usuarios.clients.CursoClientRest;
import org.atravieso.springcloud.msvc.usuarios.models.entity.Usuario;
import org.atravieso.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoClientRest cursoClient;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>)usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> porId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        // Eliminar usuario del msvc-usuarios
        usuarioRepository.deleteById(id);

        // ELiminar el usuario que está asignado al msvc-curso con el usuario
        cursoClient.eliminarCursoUsuarioPorId(id);
    }

    @Override
    public List<Usuario> listarUsuariosPorIds(List<Long> ids) {
        return (List<Usuario>)usuarioRepository.findAllById(ids);
    }

    @Override
    public Optional<Usuario> porEmail(String email) {
        return usuarioRepository.porEmail(email);
    }

    /*
    @Override
    public Optional<Usuario> porEmail(String email) {
        return usuarioRepository.porEmail(email);
    }
     */

    @Override
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }


}
