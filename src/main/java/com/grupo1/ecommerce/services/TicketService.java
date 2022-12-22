package com.grupo1.ecommerce.services;

import com.grupo1.ecommerce.dtos.PaymentApplicationDTO;
import com.grupo1.ecommerce.dtos.TicketDTO;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getTickets();

    TicketDTO getTicket(Long id);

    List<TicketDTO>  currentTicket(Client client);

    void addProdToTicket(Ticket ticket, CarritoProducto carritoProducto);

    Ticket newTicket (PaymentApplicationDTO paymentApplicationDTO, Client loggedClient);
}
