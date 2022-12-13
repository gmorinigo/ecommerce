package com.grupo1.ecommerce.dtos;

import com.grupo1.ecommerce.models.TicketProd;

public class TicketProdDTO {

    private Long id;
    private Long idProducto;
    private Integer cantidad;

    public TicketProdDTO() {
    }

    public TicketProdDTO(TicketProd ticketProd) {
        this.id = ticketProd.getId();
        this.idProducto = ticketProd.getProducto().getId();
        this.cantidad = ticketProd.getCantidad();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
