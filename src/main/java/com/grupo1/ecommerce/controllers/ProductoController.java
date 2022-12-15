package com.grupo1.ecommerce.controllers;

import com.grupo1.ecommerce.dtos.ProductoDTO;
import com.grupo1.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    ProductService productService;
    @PostMapping(path = "/products")
    public ResponseEntity<Object> createProduct(@RequestParam String productName
            , @RequestParam Integer productStock
            , @RequestParam Integer discount
            , @RequestParam Double price) {

        if (price == 0 || discount == 0 || productStock == 0 || productName.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        productService.createProduct(productName, productStock, discount, price);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/products")
    public ResponseEntity<Object> deleteProduct(@RequestParam String productName) {
        productService.deleteProduct(productName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping(path = "/products")
    public ResponseEntity<Object> updateProduct(@RequestParam String productName
            , @RequestParam Integer productStock
            , @RequestParam Integer discount
            , @RequestParam Double price
            , @RequestParam String categoria) {
        productService.updateProduct(productName, productStock, discount, price, categoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/products")
    public List<ProductoDTO> getAllProducts() {
        return productService.findAllProducts().stream().map(ProductoDTO::new).collect(Collectors.toList());
    }
}