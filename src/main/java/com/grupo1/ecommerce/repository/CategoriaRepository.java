package com.grupo1.ecommerce.repository;

import com.grupo1.ecommerce.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
