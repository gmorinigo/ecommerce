package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue()
    @GenericGenerator(name = "native" , strategy = "native")
    private Long id;

    private String firtName;
    private String LastName;
    private String email;
    private String password;

    @OneToOne(mappedBy = "ownerCarrito", cascade = CascadeType.ALL)
    private Carrito carrito;

    @OneToMany(mappedBy = "ownerTicket", fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

    public Client() {
    }

    public Client(String firtName, String lastName, String email, String password) {
        this.firtName = firtName;
        LastName = lastName;
        this.email = email;
        this.password = password;
        //this.carrito = new Carrito();
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
