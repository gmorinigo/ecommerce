package com.grupo1.ecommerce.services;

import com.grupo1.ecommerce.dtos.TicketDTO;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getTickets();

    TicketDTO getTicket(Long id);

    List<TicketDTO>  currentTicket(Client client);

    void addProdToTicket(Ticket ticket, CarritoProducto carritoProducto);

    void save(Ticket ticket);
}
