package com.grupo1.ecommerce.services;

import com.grupo1.ecommerce.dtos.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaDTO> getCategoriasDTO();

    ResponseEntity<Object> updateCategoria(UpdateCategoriaDTO updateCategoriaDTO);

    void insertCategoria(InsertCategoriaDTO insertCategoriaDTO);

    ResponseEntity<Object> deleteCategoria(DeleteCategoriaDTO deleteCategoriaDTO);

    ResponseEntity<Object> addProductoEnCategoria(InsertProductoEnCategoriaDTO insertProductoEnCategoriaDTO);
}
