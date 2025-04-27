package com.cine.ecommerce_backend.model;

public class PaymentRequest {
    
    private String numeroTarjeta;
    private String fechaExpiracion;
    private String cvv;
    private String correo;
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;

    // Getters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }
    
    public String getCvv() {
        return cvv;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    
    // Setters
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    
    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "numeroTarjeta='" + numeroTarjeta + '\'' +
                ", fechaExpiracion='" + fechaExpiracion + '\'' +
                ", cvv='" + cvv + '\'' +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                '}';
    }


}
