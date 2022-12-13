package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Ticket {

    @Id
    @GeneratedValue()
    @GenericGenerator(name = "native", strategy = "native")
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Client owner;
    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    Set<TicketProd> productos = new HashSet<>();

    public Ticket() {
    }

    public Ticket(double montoTotal, String dirreccion, String dirreccionNum, String codigoPostal, String ciudad, String numTarjeta, Integer cvv, Integer anioVencimiento, Integer mesVencimiento, Client owner) {
        this.montoTotal = montoTotal;
        this.dirreccion = dirreccion;
        this.dirreccionNum = dirreccionNum;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.anioVencimiento = anioVencimiento;
        this.mesVencimiento = mesVencimiento;
        this.owner = owner;
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

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Set<TicketProd> getProductos() {
        return productos;
    }

    public void setProductos(Set<TicketProd> productos) {
        this.productos = productos;
    }
}
