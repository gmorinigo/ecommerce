package com.grupo1.ecommerce.services.impl;

import com.grupo1.ecommerce.dtos.*;
import com.grupo1.ecommerce.models.CategProducto;
import com.grupo1.ecommerce.models.Categoria;
import com.grupo1.ecommerce.models.Producto;
import com.grupo1.ecommerce.repository.CategProductoRepository;
import com.grupo1.ecommerce.repository.CategoriaRepository;
import com.grupo1.ecommerce.repository.ProductoRepository;
import com.grupo1.ecommerce.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategProductoRepository categProductoRepository;

    @Override
    public List<CategoriaDTO> getCategoriasDTO() {
        return categoriaRepository.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> updateCategoria(UpdateCategoriaDTO updateCategoriaDTO) {
        Categoria categoria = categoriaRepository.findById(updateCategoriaDTO.getId()).orElse(null);

        if (categoria == null)
            return new ResponseEntity<>("id de categoria informado inválido", HttpStatus.FORBIDDEN);

        categoria.setNombre(updateCategoriaDTO.getNombre());

        categoriaRepository.save(categoria);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public void insertCategoria(InsertCategoriaDTO insertCategoriaDTO) {
        Categoria categoria = new Categoria(insertCategoriaDTO.getNombre());
        categoriaRepository.save(categoria);
    }

    @Override
    public ResponseEntity<Object> deleteCategoria(DeleteCategoriaDTO deleteCategoriaDTO) {
        Categoria categoria = categoriaRepository.findById(deleteCategoriaDTO.getId()).orElse(null);

        if (categoria == null)
            return new ResponseEntity<>("id de categoria informado inválido", HttpStatus.FORBIDDEN);

        categoriaRepository.delete(categoria);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addProductoEnCategoria(InsertProductoEnCategoriaDTO insertProductoEnCategoriaDTO) {
        Categoria categoria = categoriaRepository.findById(insertProductoEnCategoriaDTO.getIdCategoria()).orElse(null);

        if (categoria == null)
            return new ResponseEntity<>("id de categoria informado inválido", HttpStatus.FORBIDDEN);

        Producto producto = productoRepository.findById(insertProductoEnCategoriaDTO.getIdProducto()).orElse(null);

        if (producto == null)
            return new ResponseEntity<>("id de producto informado inválido", HttpStatus.FORBIDDEN);

        categProductoRepository.save(new CategProducto(categoria, producto));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
