package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoDTO {
    private Long id;

    private String nombre;

    private int stock;

    private Integer descuento;

    private double precio;

    private List<CategoriaDTO> categorias;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.categorias = producto.getCategorias().stream().map(categProducto -> new CategoriaDTO(categProducto.getCategoria()))
                .collect(Collectors.toList());
        this.descuento = producto.getDescuento();
        this.precio = producto.getPrecio();
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

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
