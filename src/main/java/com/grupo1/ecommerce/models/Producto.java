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

    private Integer descuento;

    private double precio;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private Set<CategProducto> categorias;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private Set<CarritoProducto> carritoProductos;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private Set<TicketProd> tickets = new HashSet<>();

    public Producto() {
    }

    public Producto(String nombre, int stock, int descuento, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.descuento = descuento;
        this.precio = precio;
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

    public Set<TicketProd> getTickets() { return tickets; }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public void setTickets(Set<TicketProd> tickets) {
        this.tickets = tickets;
    }
}