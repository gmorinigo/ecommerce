package com.grupo1.ecommerce.services.impl;

import com.grupo1.ecommerce.dtos.CarritoDTO;
import com.grupo1.ecommerce.models.Carrito;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Producto;
import com.grupo1.ecommerce.repository.CarritoProductoRepository;
import com.grupo1.ecommerce.repository.CarritoRepository;
import com.grupo1.ecommerce.repository.ProductoRepository;
import com.grupo1.ecommerce.services.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServiceImpl implements ICarritoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    CarritoProductoRepository carritoProductoRepository;

    @Override
    public ResponseEntity<Object> addProductoACarrito(Client client, Long idProducto, int cantidad) {
        Carrito carrito = client.getCarrito();
        Producto producto = productoRepository.findById(idProducto).orElse(null);

        if (producto == null)
            return new ResponseEntity<>("id de producto informado inválido", HttpStatus.FORBIDDEN);

        if (cantidad == 0)
            return new ResponseEntity<>("cantidad inválida", HttpStatus.FORBIDDEN);

        if (producto.getStock() < cantidad)
            return new ResponseEntity<>("Stock del producto " + producto.getId() + "-" + producto.getNombre()
                    + " Insuficiente", HttpStatus.FORBIDDEN);

        carritoProductoRepository.save(new CarritoProducto(cantidad, producto, carrito));

        carrito.setMontoTotal(carrito.getMontoTotal() + ((producto.getPrecio() * (100 - producto.getDescuento()) / 100) * cantidad));

        carritoRepository.save(carrito);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public CarritoDTO getProductosCarrito(Client client) {
        return new CarritoDTO(client.getCarrito());
    }

    @Override
    public void eliminarProductoCarrito(Client client, CarritoProducto carritoProducto) {
        Carrito carrito = client.getCarrito();
        Producto producto = carritoProducto.getProducto();
        carrito.setMontoTotal(carrito.getMontoTotal() - ((producto.getPrecio() * (100 - producto.getDescuento()) / 100)
                                                                               * carritoProducto.getCantidad()));

        carritoProductoRepository.delete(carritoProducto);

        carritoRepository.save(carrito);
    }

}
