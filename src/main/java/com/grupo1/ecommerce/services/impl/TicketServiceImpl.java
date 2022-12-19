package com.grupo1.ecommerce.services.impl;

import com.grupo1.ecommerce.dtos.TicketDTO;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;
import com.grupo1.ecommerce.models.TicketProd;
import com.grupo1.ecommerce.repository.TicketProdRepository;
import com.grupo1.ecommerce.repository.TicketRepository;
import com.grupo1.ecommerce.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketProdRepository ticketProdRepository;

    @Override
    public List<TicketDTO> getTickets() {
        return ticketRepository.findAll().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
    }

    @Override
    public TicketDTO getTicket(Long id) {
        return ticketRepository.findById(id).map(ticket -> new TicketDTO(ticket)).orElse(null);
    }

    @Override
    public List<TicketDTO> currentTicket(Client client) {
        return client.getTickets().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
    }

    @Override
    public void addProdToTicket(Ticket ticket, CarritoProducto carritoProducto) {
        TicketProd ticketProd = new TicketProd(carritoProducto.getCantidad(), ticket, carritoProducto.getProducto());
        ticketProdRepository.save(ticketProd);
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
