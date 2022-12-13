package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Ticket;

import java.util.Set;
import java.util.stream.Collectors;

public class TicketDTO {
    private Long id;
    private double montoTotal;
    private String dirreccion;
    private String dirreccionNum;
    private String codigoPostal;
    private String ciudad;
    private String numTarjeta;
    private Integer cvv;
    private Integer anioVencimiento;
    private Integer mesVencimiento;
    private Set<TicketProdDTO> productos;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.montoTotal = ticket.getMontoTotal();
        this.dirreccion = ticket.getDirreccion();
        this.dirreccionNum = ticket.getDirreccionNum();
        this.codigoPostal = ticket.getCodigoPostal();
        this.ciudad = ticket.getCiudad();
        this.numTarjeta = ticket.getNumTarjeta();
        this.cvv = ticket.getCvv();
        this.anioVencimiento = ticket.getAnioVencimiento();
        this.mesVencimiento = ticket.getMesVencimiento();
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

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public String getDirreccionNum() {
        return dirreccionNum;
    }

    public void setDirreccionNum(String dirreccionNum) {
        this.dirreccionNum = dirreccionNum;
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

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getAnioVencimiento() {
        return anioVencimiento;
    }

    public void setAnioVencimiento(Integer anioVencimiento) {
        this.anioVencimiento = anioVencimiento;
    }

    public Integer getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(Integer mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public Set<TicketProdDTO> getProductos() {
        return productos;
    }

    public void setProductos(Set<TicketProdDTO> productos) {
        this.productos = productos;
    }
}
