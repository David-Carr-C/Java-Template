package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data // otorga los getters, setters, toString, equals y hashCode a la clase
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users") // nombre de la tabla en la base de datos (por defecto es el nombre de la clase)
public class UserEntity {

    @Id // indica que es la clave primaria
    @GeneratedValue(strategy = IDENTITY) // delega la generación de la clave primaria a la base de datos
    private Long id;

    @NotBlank
    @Size(max = 80) // max = 255 characters
    private String username;

    @Email // valida que el campo sea un email con regex: https://www.baeldung.com/java-email-validation-regex
    @NotBlank
    @Size(max = 30)
    private String email;

    @NotBlank
    private String password;

    @ManyToMany(fetch = EAGER, targetEntity = RoleEntity.class, cascade = PERSIST) // relacion muchos a muchos, con fetch eager (carga los roles al cargar el usuario), target entity es la clase destino de la relación, cascade persist indica que se persistan (no se modifiquen) los roles al persistir (eliminar, cambiar o modificar) el usuario
    @JoinTable( // indica que se debe crear una tabla intermedia para la relación
            name = "users_roles", // nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "user_id"), // nombre de la columna que hace referencia a la tabla actual
            inverseJoinColumns = @JoinColumn(name = "role_id") // nombre de la columna que hace referencia a la tabla destino
    )
    private Set<RoleEntity> roles;
}

/*
Esta clase es la representación de la tabla users en la base de datos, cada campo es una columna de la tabla.

@Entity: indica que es una entidad de la base de datos, es decir, que se debe persistir en la base de datos.

@Id: indica que es la clave primaria de la tabla.

@GeneratedValue: indica que la clave primaria es generada por la base de datos.

@NotBlank: indica que el campo no puede ser nulo ni vacío.

@Size: indica el tamaño máximo del campo.

@ManyToMany: indica que es una relación muchos a muchos.

@JoinTable: indica que se debe crear una tabla intermedia para la relación.

@JoinColumn: indica el nombre de la columna que hace referencia a la tabla actual y a la tabla destino.

@Builder: indica que se debe crear un builder para la clase, es decir, un stream constructor.

@Data: otorga los getters, setters, toString, equals y hashCode a la clase.

@AllArgsConstructor: crea un constructor con todos los campos de la clase.

@NoArgsConstructor: crea un constructor vacío.
*/
