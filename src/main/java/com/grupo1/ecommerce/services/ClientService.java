package com.grupo1.ecommerce.services;

import com.grupo1.ecommerce.dtos.ClientDTO;
import com.grupo1.ecommerce.models.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> findAll();

    ClientDTO findById(Long id);

    Client findByEmail(String email);

    void deleteClient(Client client);

    void updateClient(String firstName, String lastName, String email, String password, Client clienteActulizar);

    void createClient(String firstName, String lastName, String email, String password);
}
