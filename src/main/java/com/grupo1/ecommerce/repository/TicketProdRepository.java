package com.grupo1.ecommerce.repository;

import com.grupo1.ecommerce.models.TicketProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketProdRepository extends JpaRepository<TicketProd,Long> {
}
