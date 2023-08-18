package org.atravieso.springcloud.msvc.cursos.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cursos_usuarios")
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object obj) {
        // Si son iguales comparando por instancia/referencia se trata del mismo objeto
        if(this == obj) {
            return true;
        }

        // Si el objeto no es una instancia de CursoUsuario
        if(!(obj instanceof CursoUsuario)) {
            return false;
        }

        // Cast al objeto que se recibe al tipo de dato Object necesitado (CursoUsuario)
        CursoUsuario o = (CursoUsuario) obj;

        // Si la instancia tiene un usuarioId comparo el usuarioId de la instancia con el del obj que llega por parámetro
        return this.usuarioId != null && this.usuarioId.equals(o.usuarioId);
    }
}
