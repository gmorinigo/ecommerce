package com.grupo1.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria",fetch = FetchType.EAGER)
    private List<CategProducto> productos;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
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

    public List<CategProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CategProducto> productos) {
        this.productos = productos;
    }
}
