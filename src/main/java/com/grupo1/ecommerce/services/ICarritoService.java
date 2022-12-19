package com.grupo1.ecommerce.services;

import com.grupo1.ecommerce.dtos.CarritoDTO;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import org.springframework.http.ResponseEntity;

public interface ICarritoService {
    ResponseEntity<Object> addProductoACarrito(Client client, Long idProducto, int cantidad);

    CarritoDTO getProductosCarrito(Client client);

    void eliminarProductoCarrito(Client cliente, CarritoProducto carritoProducto);
}
