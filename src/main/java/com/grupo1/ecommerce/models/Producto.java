package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;

    private String nombre;

    private int stock;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private Set<CategProducto> categorias;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private Set<CarritoProducto> carritoProductos;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private Set<TicketProd> tickets = new HashSet<>();

    public Producto() {
    }

    public Producto(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<CategProducto> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<CategProducto> categorias) {
        this.categorias = categorias;
    }

    public Set<CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(Set<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }

    public Set<TicketProd> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketProd> tickets) {
        this.tickets = tickets;
    }
}
