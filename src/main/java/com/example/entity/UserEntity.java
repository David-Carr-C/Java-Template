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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users") // asigna el nombre de la tabla en la base de datos (por defecto es el nombre de la clase)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    private String username;

    @Email
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

/**
 * Esta clase es la representación de la tabla users en la base de datos, cada campo es una columna de la tabla.
 *
 * @Data de Lombok es para generar los getters, setters, toString, equals y hashCode a la clase.
 * @AllArgsConstructor de Lombok es para generar un constructor con todos los parámetros.
 * @NoArgsConstructor de Lombok es para generar un constructor sin parámetros.
 * @Builder de Lombok indica que se puede crear un builder para la clase, es decir, un stream constructor.
 *
 * @Id de JPA para indicar que el campo es la clave primaria.
 * @GeneratedValue de JPA para indicar que el campo es autoincremental y dependiendo de la estrategia que se le pase, delega la generación de la clave primaria a la base de datos.
 * @NotBlank de Bean Validation para indicar que el campo no puede ser nulo ni vacío.
 * @Size de Bean Validation para indicar el tamaño máximo del campo, max = 255 characters.
 * @ManyToMany de JPA para indicar que es una relación muchos a muchos.
 * @JoinTable de JPA para indicar que se debe crear una tabla intermedia para la relación.
 * @JoinColumn de JPA para indicar el nombre de la columna que hace referencia a la tabla actual y a la tabla destino.
 *
 * @Entity de JPA para indicar que es una entidad de la base de datos, es decir, que se debe persistir en la base de datos.
 * @Table de JPA para indicar el nombre de la tabla en la base de datos (por defecto es el nombre de la clase).
 * @Enumerated de JPA para indicar que el campo es de tipo enumerado.
 * @Enumerated(STRING) de JPA para indicar que el campo es de tipo enumerado de tipo String.
 * @ManyToMany(fetch = EAGER, targetEntity = RoleEntity.class, cascade = PERSIST) de JPA para indicar que es una relación muchos a muchos, con fetch eager (carga los roles al cargar el usuario), target entity es la clase destino de la relación, cascade persist indica que se persistan (no se modifiquen) los roles al persistir (eliminar, cambiar o modificar) el usuario.
 * @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")) de JPA para indicar que se debe crear una tabla intermedia para la relación, name es el nombre de la tabla intermedia, joinColumns es el nombre de la columna que hace referencia a la tabla actual, inverseJoinColumns es el nombre de la columna que hace referencia a la tabla destino.
 *
 * @Email de Bean Validation para validar que el campo sea un email con regex: https://www.baeldung.com/java-email-validation-regex
 */
