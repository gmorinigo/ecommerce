package com.grupo1.ecommerce.services;

import com.grupo1.ecommerce.models.Producto;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    Producto createProduct (String productName, Integer stock, Integer productDiscount, Double productPrice);
    void deleteProduct (String productName);
    Producto updateProduct (String name, Integer stock, Integer productDiscount, Double productPrice, String categoria);
    Producto findProductByName (String productName);
    List<Producto> findAllProducts ();
}
