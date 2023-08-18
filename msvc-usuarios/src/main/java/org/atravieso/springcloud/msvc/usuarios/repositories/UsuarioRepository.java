package org.atravieso.springcloud.msvc.usuarios.repositories;


import org.atravieso.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email=?1")
    Optional<Usuario> porEmail(String email);


    // Solo devuelve el True si es que existe el correo
    boolean existsByEmail(String email);

}
