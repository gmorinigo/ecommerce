package com.grupo1.ecommerce.controllers;


import com.grupo1.ecommerce.dtos.ClientDTO;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.services.ClientService;
import com.grupo1.ecommerce.services.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable long id) {
        return clientService.findById(id);
    }

    @PostMapping("/clients")

    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Datos invalidos", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Nombre en uso", HttpStatus.FORBIDDEN);
        }

        clientService.createClient(firstName, lastName, email, password);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO CurrentClient(Authentication authentication) {

        Client clientAuth = clientService.findByEmail(authentication.getName());

        return clientService.findById(clientAuth.getId());
    }

    @DeleteMapping("/clients/delete")
    public ResponseEntity<Object> deleteCliente(@PathVariable String email){

        if (clientService.findByEmail(email) ==  null) {
            return new ResponseEntity<>("No existe cuenta", HttpStatus.FORBIDDEN);
        }

        Client client = clientService.findByEmail(email);
        clientService.deleteClient(client);

        return new ResponseEntity<>(HttpStatus.CONTINUE);
    }

    @PutMapping("/clients/update")
    public ResponseEntity<Object> actualizar(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (clientService.findByEmail(email) ==  null) {
            return new ResponseEntity<>("No existe cuenta", HttpStatus.FORBIDDEN);
        }

        Client clienteActualizar = clientService.findByEmail(email);

        if (firstName.isEmpty() && lastName.isEmpty() && password.isEmpty()) {
            return new ResponseEntity<>("Datos invalidos", HttpStatus.FORBIDDEN);
        }

        clientService.updateClient(firstName, lastName, email, password, clienteActualizar);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
