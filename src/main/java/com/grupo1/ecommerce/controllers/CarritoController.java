package com.grupo1.ecommerce.controllers;

import com.grupo1.ecommerce.dtos.CarritoDTO;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.services.ClientService;
import com.grupo1.ecommerce.services.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarritoController {

    @Autowired
    ICarritoService carritoService;

    @Autowired
    ClientService clientService;

    @PostMapping("/carrito/addproducto")
    public ResponseEntity<Object> addProductoACarrito(Authentication authentication, @RequestParam Long idProducto,
                                                      @RequestParam int cantidad){
        Client clientAuth = clientService.findByEmail(authentication.getName());

        return carritoService.addProductoACarrito(clientAuth, idProducto, cantidad);
    }

    @GetMapping("/carrito")
    public CarritoDTO getProductosCarrito (Authentication authentication){
        Client clientAuth = clientService.findByEmail(authentication.getName());

        return carritoService.getProductosCarrito(clientAuth);
    }

}
