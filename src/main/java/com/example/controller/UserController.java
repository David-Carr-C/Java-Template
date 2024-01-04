package com.example.controller;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.service.implementation.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping()
    @ResponseBody
    public Iterable<UserEntity> getUsers() {
        logger.debug("getUsers() called");
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Iterable<UserEntity> getUser(@PathVariable Long id) {
        logger.debug("getUser() called");
        return userService.getUser(id);
    }

    @PostMapping()
    public ResponseEntity<String> postUser(@Valid @RequestBody UserDTO userDTO) {
        logger.debug("postUser() called");
        return userService.postUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        logger.debug("deleteUser() called");
        return userService.deleteUser(id);
    }
}

/*
@RestController indica que esta clase es un controlador que maneja peticiones REST/Http

@RequestMapping("/user") indica que este objeto maneja peticiones que comienzan con /user

/{id} indica que el parámetro id se debe pasar en la url, por ejemplo: "/user/1
el cual se recupera luego con @PathVariable Long id

@GetMapping() indica que este método maneja peticiones GET
@PostMapping() indica que este método maneja peticiones POST
@PutMapping() indica que este método maneja peticiones PUT
@DeleteMapping() indica que este método maneja peticiones DELETE

@ResponseBody indica que el valor devuelto por el método debe ser escrito directamente en el cuerpo de la respuesta

Un ResponseEntity<T> es una clase que representa toda la respuesta HTTP. Puedes controlar cualquier cosa que suceda:
códigos de estado, encabezados y cuerpo de la respuesta. Por lo general, se usa para devolver el código de estado

@AutoWired es una anotación que le dice a Spring que inyecte una dependencia, lo que significa que Spring
creará una instancia de UserService con todos los campos que necesite y los inyectará en UserController

logger no puede ser autowired porque es un objeto estático, por lo que no se puede crear una instancia de él
dado a que tiene:
// private constructor prevents instantiation
    private LoggerFactory() {
    }
Un constructor privado no se puede llamar desde fuera de la clase, por lo que no se puede crear una instancia de él
Solo se puede crear a traves de su metodo factory
*/
