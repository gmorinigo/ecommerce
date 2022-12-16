package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.CarritoProducto;

import java.util.List;
import java.util.stream.Collectors;

public class CarritoProductoDTO {
    private Long id;

    private int cantidad;

    private String nombre;

    private int stockRestante;

    private Integer descuento;

    private double precio;

    private List<String> categorias;

    public CarritoProductoDTO(CarritoProducto carritoProducto) {
        this.id = carritoProducto.getProducto().getId();
        this.cantidad = carritoProducto.getCantidad();
        this.nombre = carritoProducto.getProducto().getNombre();
        this.stockRestante = carritoProducto.getProducto().getStock() - carritoProducto.getCantidad();
        this.categorias = carritoProducto.getProducto().getCategorias().stream().map(categProducto -> categProducto.getCategoria().getNombre())
                .collect(Collectors.toList());
        this.descuento = carritoProducto.getProducto().getDescuento();
        this.precio = carritoProducto.getProducto().getPrecio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStockRestante() {
        return stockRestante;
    }

    public void setStockRestante(int stockRestante) {
        this.stockRestante = stockRestante;
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

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
}
