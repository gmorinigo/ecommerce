package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Carrito;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;

import java.util.Set;

public class ClientDTO {
    private Long id;

    private String firtName;
    private String LastName;
    private String email;
    private String password;
    private Carrito carrito;
    private Set<Ticket> tickets;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firtName = client.getFirtName();
        LastName = client.getLastName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.carrito = client.getCarrito();
        this.tickets = client.getTickets();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
