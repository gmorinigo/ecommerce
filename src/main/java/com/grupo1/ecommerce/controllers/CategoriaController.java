package com.grupo1.ecommerce.controllers;

import com.grupo1.ecommerce.dtos.*;
import com.grupo1.ecommerce.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<CategoriaDTO> getCategorias (){
        return categoriaService.getCategoriasDTO();
    }

    @PostMapping("/categorias")
    public ResponseEntity<Object> insertCategoria (@RequestBody InsertCategoriaDTO insertCategoriaDTO){
        if (insertCategoriaDTO.getNombre().isEmpty())
            return new ResponseEntity<>("Debe informar nombre de la categoria", HttpStatus.FORBIDDEN);

        categoriaService.insertCategoria(insertCategoriaDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/categorias")
    public ResponseEntity<Object> updateCategoria (@RequestBody UpdateCategoriaDTO updateCategoriaDTO){
        if (updateCategoriaDTO.getId() == 0 || updateCategoriaDTO.getNombre().isEmpty())
            return new ResponseEntity<>("Datos informados insuficientes", HttpStatus.FORBIDDEN);

        return categoriaService.updateCategoria(updateCategoriaDTO);
    }

    @DeleteMapping("/categorias")
    public ResponseEntity<Object> deleteCategoria (@RequestBody DeleteCategoriaDTO deleteCategoriaDTO){
        if (deleteCategoriaDTO.getId() == 0 || deleteCategoriaDTO.getId() == null)
            return new ResponseEntity<>("Debe informar el id de la categoria", HttpStatus.FORBIDDEN);

        return categoriaService.deleteCategoria(deleteCategoriaDTO);
    }

    @PostMapping("/categorias/addproducto")
    public ResponseEntity<Object> addProductoEnCategoria (@RequestBody InsertProductoEnCategoriaDTO insertCategoriaDTO){
        if (insertCategoriaDTO.hasValidData())
            return new ResponseEntity<>("Debe informar nombre de la categoria", HttpStatus.FORBIDDEN);

        return (categoriaService.addProductoEnCategoria(insertCategoriaDTO));
    }
}
