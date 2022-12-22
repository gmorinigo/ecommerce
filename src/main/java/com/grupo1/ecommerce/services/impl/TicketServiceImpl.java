package com.grupo1.ecommerce.services.impl;

import com.grupo1.ecommerce.dtos.PaymentApplicationDTO;
import com.grupo1.ecommerce.dtos.PaymentMethodDTO;
import com.grupo1.ecommerce.dtos.TicketDTO;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;
import com.grupo1.ecommerce.models.TicketProd;
import com.grupo1.ecommerce.repository.TicketProdRepository;
import com.grupo1.ecommerce.repository.TicketRepository;
import com.grupo1.ecommerce.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketProdRepository ticketProdRepository;
    @Autowired
    RestTemplate restTemplate;

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
    public Ticket newTicket (PaymentApplicationDTO paymentApplicationDTO, Client loggedClient) {
        PaymentMethodDTO paymentMethod = new PaymentMethodDTO(
                 loggedClient.getEmail()
                ,paymentApplicationDTO.getNumTarjeta()
                ,paymentApplicationDTO.getCvv()
                ,paymentApplicationDTO.getAnioVencimiento()
                ,paymentApplicationDTO.getMesVencimiento());
        ResponseEntity<Object> validatePaymentMethod =
                restTemplate.postForEntity("https://homebanking-production-0187.up.railway.app//api/clients/validate/card"
                        , paymentMethod
                        , Object.class);
        if (validatePaymentMethod.getStatusCode().equals(HttpStatus.OK)) {
            return ticketRepository.save(new Ticket(
                      loggedClient.getCarrito().getMontoTotal()
                    , paymentApplicationDTO.getDireccion()
                    , paymentApplicationDTO.getDireccionNum()
                    , paymentApplicationDTO.getCodigoPostal()
                    , paymentApplicationDTO.getCiudad()
                    , "XXXX-XXXX-XXXX-" + paymentApplicationDTO.getNumTarjeta().substring(15)
                    , LocalDateTime.now()
                    , loggedClient));
        } else {
            return new Ticket();
        }
    }
}