package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;

    private String nombre;

    private int stock;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private List<CategProducto> categorias;

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

    public List<CategProducto> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategProducto> categorias) {
        this.categorias = categorias;
    }
}
