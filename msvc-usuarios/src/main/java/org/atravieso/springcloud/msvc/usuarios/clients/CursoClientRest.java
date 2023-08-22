package org.atravieso.springcloud.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos", url = "localhost:8082")
public interface CursoClientRest {
    @DeleteMapping("/eliminar-curso-usuario/{usuarioId}")
    public void eliminarCursoUsuarioPorId(@PathVariable Long usuarioId);

}
