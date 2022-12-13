package com.grupo1.ecommerce.repository;

import com.grupo1.ecommerce.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarritoRepository extends JpaRepository<Carrito,Long> {
}
