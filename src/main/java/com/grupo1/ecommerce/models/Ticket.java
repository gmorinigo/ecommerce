package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private double montoTotal;
    private String direccion;
    private String direccionNum;
    private String codigoPostal;
    private String ciudad;
    private String paymentMethod;
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Client ownerTicket;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    Set<TicketProd> productos = new HashSet<>();

    public Ticket() {
    }

    public Ticket(double montoTotal
            , String direccion
            , String direccionNum
            , String codigoPostal
            , String ciudad
            , String paymentMethod
            , LocalDateTime creationDate
            , Client owner) {
        this.montoTotal = montoTotal;
        this.direccion = direccion;
        this.direccionNum = direccionNum;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.paymentMethod = paymentMethod;
        this.creationDate = creationDate;
        this.ownerTicket = owner;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Client getOwnerTicket() {
        return ownerTicket;
    }

    public void setOwnerTicket(Client ownerTicket) {
        this.ownerTicket = ownerTicket;
    }

    public Set<TicketProd> getProductos() {
        return productos;
    }

    public void setProductos(Set<TicketProd> productos) {
        this.productos = productos;
    }
}
