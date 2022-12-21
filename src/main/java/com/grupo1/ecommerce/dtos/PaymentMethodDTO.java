package com.grupo1.ecommerce.dtos;

public class PaymentMethodDTO {
    private String email;
    private String numTarjeta;
    private Integer cvv;
    private Integer anioVencimiento;
    private Integer mesVencimiento;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(String email, String numTarjeta, Integer cvv, Integer anioVencimiento, Integer mesVencimiento) {
        this.email = email;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.anioVencimiento = anioVencimiento;
        this.mesVencimiento = mesVencimiento;
    }

    public String getEmail() {
        return email;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public Integer getCvv() {
        return cvv;
    }

    public Integer getAnioVencimiento() {
        return anioVencimiento;
    }

    public Integer getMesVencimiento() {
        return mesVencimiento;
    }
}
