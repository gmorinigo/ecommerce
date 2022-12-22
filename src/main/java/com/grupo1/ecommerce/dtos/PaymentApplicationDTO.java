package com.grupo1.ecommerce.dtos;

public class PaymentApplicationDTO {
    private double montoTotal;
    private String direccion;
    private String direccionNum;
    private String codigoPostal;
    private String ciudad;
    private String numTarjeta;
    private Integer cvv;
    private Integer anioVencimiento;
    private Integer mesVencimiento;

    public PaymentApplicationDTO() {
    }

    public PaymentApplicationDTO(double montoTotal
            , String direccion
            , String direccionNum
            , String codigoPostal
            , String ciudad
            , String numTarjeta
            , Integer cvv
            , Integer anioVencimiento
            , Integer mesVencimiento) {
        this.montoTotal = montoTotal;
        this.direccion = direccion;
        this.direccionNum = direccionNum;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.anioVencimiento = anioVencimiento;
        this.mesVencimiento = mesVencimiento;
    }


    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionNum() {
        return direccionNum;
    }

    public void setDireccionNum(String direccionNum) {
        this.direccionNum = direccionNum;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getAnioVencimiento() {
        return anioVencimiento;
    }

    public void setAnioVencimiento(Integer anioVencimiento) {
        this.anioVencimiento = anioVencimiento;
    }

    public Integer getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(Integer mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

}
