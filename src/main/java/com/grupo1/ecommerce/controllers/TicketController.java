package com.grupo1.ecommerce.controllers;

import com.grupo1.ecommerce.dtos.TicketApplicationDTO;
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
    public List<TicketDTO>  currentTicket(Authentication authentication) {

        Client clientAuth = clientService.findByEmail(authentication.getName());
        return ticketService.currentTicket(clientAuth);
    }

    @Transactional
    @PostMapping("/tickets")
    public ResponseEntity<String> addTicket(@RequestBody TicketApplicationDTO ticketApplicationDTO, Authentication authentication) {

        Client clientAuth = clientService.findByEmail(authentication.getName());

        Ticket ticket = new Ticket(ticketApplicationDTO.getMontoTotal(),
                ticketApplicationDTO.getDirreccion(),
                ticketApplicationDTO.getDirreccionNum(),
                ticketApplicationDTO.getCodigoPostal(),
                ticketApplicationDTO.getCiudad(),
                ticketApplicationDTO.getNumTarjeta(),
                ticketApplicationDTO.getCvv(),
                ticketApplicationDTO.getAnioVencimiento(),
                ticketApplicationDTO.getMesVencimiento(),
                clientAuth);

        ticketService.save(ticket);

        for (CarritoProducto carritoProducto : clientAuth.getCarrito().getCarritosProducto()) {
            ticketService.addProdToTicket(ticket, carritoProducto);
            carritoService.eliminarProductoCarrito(clientAuth, carritoProducto);
        }

        // ENVIO DE MAIL
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(clientAuth.getEmail());
        email.setFrom("Grupo1.ecommerce.MindHub@gmail.com");
        email.setSubject("Comprobate de pago - orden de compra #" + ticket.getId());
        email.setText("Se genero el pago correcto por una compra de $ " + ticket.getMontoTotal() + " pagado con la tarjeta XXXX-XXXX-XXXX-" + ticket.getNumTarjeta().substring(15));

        javaMailSender.send(email);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
