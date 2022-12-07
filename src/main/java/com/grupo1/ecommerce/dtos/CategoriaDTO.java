package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDTO {
    private Long id;

    private String nombre;

    private List<ProductoDTO> productos;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
        this.productos = categoria.getProductos().stream().map(categProducto -> new ProductoDTO(categProducto.getProducto()))
                .collect(Collectors.toList());
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

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
