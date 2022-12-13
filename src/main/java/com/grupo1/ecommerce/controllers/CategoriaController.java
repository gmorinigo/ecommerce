package com.grupo1.ecommerce.controllers;

import com.grupo1.ecommerce.dtos.CategoriaDTO;
import com.grupo1.ecommerce.services.impl.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    CategoriaServiceImpl categoriaService;

    @GetMapping(/categorias)
    public List<CategoriaDTO> getCategorias (){
        return null
    }
}
