package com.grupo1.ecommerce.dtos;

public class InsertProductoEnCategoriaDTO {

    private Long idCategoria;

    private Long idProducto;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public boolean hasValidData() {
        return (idProducto == 0 || idCategoria == 0);
    }
}
