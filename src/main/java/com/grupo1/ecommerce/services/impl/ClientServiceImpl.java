package com.grupo1.ecommerce.services.impl;

import com.grupo1.ecommerce.dtos.ClientDTO;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.repository.ClientRepository;
import com.grupo1.ecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<ClientDTO> findAll(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    public ClientDTO findById(Long id){
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    public Client findByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    public void deleteClient(Client client){
        clientRepository.delete(client);
    }

    public void updateClient(String firstName, String lastName, String email, String password, Client clienteActulizar){
        if (!firstName.isEmpty()){
            clienteActulizar.setFirtName(firstName);
            clientRepository.save(clienteActulizar);
        }

        if (!lastName.isEmpty()){
            clienteActulizar.setLastName(lastName);
            clientRepository.save(clienteActulizar);
        }

        if (!password.isEmpty()){
            clienteActulizar.setPassword(passwordEncoder.encode(password));
            clientRepository.save(clienteActulizar);
        }
    }

    public void createClient(String firstName, String lastName, String email, String password){
        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientRepository.save(client);
    }
}
