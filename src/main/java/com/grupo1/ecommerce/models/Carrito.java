package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Carrito {
    @Id
    @GenericGenerator(name = "native",strategy = "native")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montoTotal;

    @OneToMany(mappedBy = "carrito",fetch = FetchType.EAGER)
    private Set<CarritoProducto> carritosProducto;

    public Carrito() {
    }

    public Carrito(double montoTotal) {
        this.montoTotal = montoTotal;
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

    public Set<CarritoProducto> getCarritosProducto() {
        return carritosProducto;
    }

    public void setCarritosProducto(Set<CarritoProducto> carritosProducto) {
        this.carritosProducto = carritosProducto;
    }
}
