package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.Carrito;

import java.util.List;
import java.util.stream.Collectors;

public class CarritoDTO {
    private double montoTotal;

    private List<CarritoProductoDTO> productos;

    public CarritoDTO(Carrito carrito) {
        this.montoTotal = carrito.getMontoTotal();

        productos = carrito.getCarritosProducto().stream().map(CarritoProductoDTO::new).collect(Collectors.toList());
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<CarritoProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoProductoDTO> productos) {
        this.productos = productos;
    }
}
