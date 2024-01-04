package com.example.entity;

import com.example.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private ERole role;
}

/*
Esta clase se crea para representar un rol de usuario en la base de datos.

@Data de Lombok es para generar los getters y setters.
@AllArgsConstructor de Lombok es para generar un constructor con todos los parámetros.
@NoArgsConstructor de Lombok es para generar un constructor sin parámetros.
@Builder de Lombok es para generar un constructor con todos los parámetros a partir de un builder().

@Id de JPA para indicar que el campo es la clave primaria.
@GeneratedValue de JPA para indicar que el campo es autoincremental.
@Enumerated de JPA para indicar que el campo es de tipo enumerado.
@Enumerated(STRING) de JPA para indicar que el campo es de tipo enumerado de tipo String.
*/