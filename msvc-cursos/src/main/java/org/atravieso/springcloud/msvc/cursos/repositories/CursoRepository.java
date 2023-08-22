package org.atravieso.springcloud.msvc.cursos.repositories;

import org.atravieso.springcloud.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    @Modifying // Con un Delete, Update, Insert se debe complementar con la notación Query para que se realice efectivamente la operación
    @Query("DELETE FROM CursoUsuario cu WHERE cu.usuarioId = ?1")
    void eliminarCursoUsuarioPorId(Long id);

}
