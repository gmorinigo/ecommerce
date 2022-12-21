package com.grupo1.ecommerce.services.impl;

import com.grupo1.ecommerce.models.Producto;
import com.grupo1.ecommerce.repository.ProductoRepository;
import com.grupo1.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductoRepository productoRepository;
    @Override
    public Producto createProduct (String productName, Integer stock, Integer productDiscount, Double productPrice) {
        return productoRepository.save(new Producto(productName, stock, productDiscount, productPrice));
    }
    @Override
    public void deleteProduct (String productName) {
        Producto product = productoRepository.findByNombre(productName);
        productoRepository.delete(product);
    }
    @Override
    public Producto updateProduct (String productName, Integer stock, Integer productDiscount, Double productPrice, String categoria) {
        Producto product = productoRepository.findByNombre(productName);
        if (!(stock == null)) {
            product.setStock(stock);
        }
        if (!(productDiscount == null)) {
            product.setDescuento(productDiscount);
        }
        if (!(productPrice == null)) {
            product.setPrecio(productPrice);
        }
        return productoRepository.save(product);
    }
    @Override
    public Producto findProductByName (String productName) {
        return productoRepository.findByNombre(productName);
    }
    @Override
    public List<Producto> findAllProducts () {
        return new ArrayList<>(productoRepository.findAll());
    }
}
