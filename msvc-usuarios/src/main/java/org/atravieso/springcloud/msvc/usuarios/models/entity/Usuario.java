package org.atravieso.springcloud.msvc.usuarios.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
    @NotEmpty es solo para tipos String, para tipos distintos a String que sean de referencia
    como Long, Integer, Date o cualquier otro objeto usamos @NotNull
     */

    @NotEmpty(message = "El campo nombre no puede estar vacio") // Validación por si viene vacio (es requerido)
    private String nombre;

    @Column(unique = true)
    @Email // Validar que sea un email válido
    @NotEmpty
    private String email;

    @NotBlank
    private String password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
