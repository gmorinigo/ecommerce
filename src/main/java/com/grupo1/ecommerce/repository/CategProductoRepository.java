package com.grupo1.ecommerce.repository;

import com.grupo1.ecommerce.models.CategProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategProductoRepository extends JpaRepository<CategProducto,Long> {
}
