package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Carrito;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;

import java.util.Set;
import java.util.stream.Collectors;



public class ClientDTO {
    private Long id;

    private String firtName;
    private String LastName;
    private String email;
    private String password;
    private CarritoDTO carrito;
    private Set<TicketDTO> tickets;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firtName = client.getFirtName();
        LastName = client.getLastName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.carrito = new CarritoDTO(client.getCarrito());
        this.tickets = client.getTickets().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toSet());
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

    public CarritoDTO getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
