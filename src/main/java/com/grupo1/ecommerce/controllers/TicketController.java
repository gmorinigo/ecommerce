package com.grupo1.ecommerce.controllers;

import com.grupo1.ecommerce.dtos.PaymentApplicationDTO;
import com.grupo1.ecommerce.dtos.TicketDTO;
import com.grupo1.ecommerce.models.CarritoProducto;
import com.grupo1.ecommerce.models.Client;
import com.grupo1.ecommerce.models.Ticket;
import com.grupo1.ecommerce.services.ClientService;
import com.grupo1.ecommerce.services.ICarritoService;
import com.grupo1.ecommerce.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    ClientService clientService;

    @Autowired
    ICarritoService carritoService;

    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/tickets")
    public List<TicketDTO> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/tickets/{id}")
    public TicketDTO getTicket(@PathVariable long id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/clients/current/tickets")
    public List<TicketDTO> currentTicket(Authentication authentication) {

        Client clientAuth = clientService.findByEmail(authentication.getName());
        return ticketService.currentTicket(clientAuth);
    }

    @Transactional
    @PostMapping("/tickets")
    public ResponseEntity<String> addTicket(@RequestBody PaymentApplicationDTO paymentApplicationDTO, Authentication authentication) {

        Client clientAuth = clientService.findByEmail(authentication.getName());

        if (paymentApplicationDTO.getCiudad().isEmpty()
        || paymentApplicationDTO.getCodigoPostal().isEmpty()
        || paymentApplicationDTO.getDireccion().isEmpty()
        || paymentApplicationDTO.getDireccionNum().isEmpty()) {
            return new ResponseEntity<>("Location missing data", HttpStatus.FORBIDDEN);
        }

        if (paymentApplicationDTO.getNumTarjeta().isEmpty()
        || paymentApplicationDTO.getCvv() == 0
        || paymentApplicationDTO.getMesVencimiento() == 0
        || paymentApplicationDTO.getAnioVencimiento() == 0) {
            return new ResponseEntity<>("Payment card missing data", HttpStatus.FORBIDDEN);
        }

        Ticket newTicket = ticketService.newTicket(paymentApplicationDTO, clientAuth);
        if (newTicket == null) {
            return new ResponseEntity<>("Check payment method", HttpStatus.FORBIDDEN);
        }

        for (CarritoProducto carritoProducto : clientAuth.getCarrito().getCarritosProducto()) {
            ticketService.addProdToTicket(newTicket, carritoProducto);
        }

        List<CarritoProducto> carritos = clientAuth.getCarrito().getCarritosProducto().stream().collect(Collectors.toList());

        for (CarritoProducto carritoProducto : carritos) {
            carritoService.eliminarProductoCarrito(clientAuth, carritoProducto);
        }

        // ENVIO DE MAIL
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(clientAuth.getEmail());
        email.setFrom("Grupo1.ecommerce.MindHub@gmail.com");
        email.setSubject("Comprobante de pago - orden de compra #" + newTicket.getId());
        email.setText("Se genero el pago correcto por una compra de $ " + newTicket.getMontoTotal() + " pagado con la tarjeta " + newTicket.getPaymentMethod());

        javaMailSender.send(email);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
