package com.viajaYa.viajaYa.models.dtos;

import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.enums.EstadoPago;
import com.viajaYa.viajaYa.models.enums.MetodoPago;

import java.util.Date;

public class FacturaDTO {
    private Long id;
    private Date fecha;
    private double total;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;
    private String xml;
    private Long idReserva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
}
