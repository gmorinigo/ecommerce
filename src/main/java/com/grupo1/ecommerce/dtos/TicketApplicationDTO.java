package com.grupo1.ecommerce.dtos;

public class TicketApplicationDTO {


    private double montoTotal;
    private String dirreccion;
    private String dirreccionNum;
    private String codigoPostal;
    private String ciudad;
    private String numTarjeta;
    private Integer cvv;
    private Integer anioVencimiento;
    private Integer mesVencimiento;

    public TicketApplicationDTO() {
    }

    public TicketApplicationDTO(double montoTotal, String dirreccion, String dirreccionNum, String codigoPostal, String ciudad, String numTarjeta, Integer cvv, Integer anioVencimiento, Integer mesVencimiento) {
        this.montoTotal = montoTotal;
        this.dirreccion = dirreccion;
        this.dirreccionNum = dirreccionNum;
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

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public String getDirreccionNum() {
        return dirreccionNum;
    }

    public void setDirreccionNum(String dirreccionNum) {
        this.dirreccionNum = dirreccionNum;
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
