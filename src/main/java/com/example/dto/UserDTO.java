package com.example.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Set<String> roles;
}

/*
Esta clase se crea para recibir los datos de un usuario desde el cliente (DTO = Data Transfer Object).

@Data de Lombok es para generar los getters y setters.
@AllArgsConstructor de Lombok es para generar un constructor con todos los parámetros.
@NoArgsConstructor de Lombok es para generar un constructor sin parámetros.

@NotBlank de Jakarta Bean Validation para indicar que el campo no puede estar vacío.
@Email de Jakarta Bean Validation para indicar que el campo debe ser un email válido.
*/