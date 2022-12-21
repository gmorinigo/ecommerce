package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Ticket;

import java.util.Set;
import java.util.stream.Collectors;

public class TicketDTO {
    private Long id;
    private double montoTotal;
    private String direccion;
    private String direccionNum;
    private String codigoPostal;
    private String ciudad;
    private String paymentMethod;
    private Integer productsQuantity;
    private Set<TicketProdDTO> productos;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.montoTotal = ticket.getMontoTotal();
        this.direccion = ticket.getDireccion();
        this.direccionNum = ticket.getDireccionNum();
        this.codigoPostal = ticket.getCodigoPostal();
        this.ciudad = ticket.getCiudad();
        this.paymentMethod = ticket.getPaymentMethod();
        this.productsQuantity = ticket.getProductos().size();
        this.productos = ticket.getProductos().stream().map(ticketProd -> new TicketProdDTO(ticketProd)).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionNum() {
        return direccionNum;
    }

    public void setDireccionNum(String direccionNum) {
        this.direccionNum = direccionNum;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPaymentMethod() { return paymentMethod; }

    public Integer getProductsQuantity() { return productsQuantity; }

    public Set<TicketProdDTO> getProductos() {
        return productos;
    }

    public void setProductos(Set<TicketProdDTO> productos) {
        this.productos = productos;
    }
}
