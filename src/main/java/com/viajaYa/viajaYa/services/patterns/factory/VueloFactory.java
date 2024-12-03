package com.viajaYa.viajaYa.services.patterns.factory;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;

public class VueloFactory {
    public static VueloModel crearVuelo(VueloDTO vueloDTO) {
        VueloModel vuelo = new VueloModel();
        vuelo.setNumVuelo(vueloDTO.getNumVuelo());
        vuelo.setAerolinea(vueloDTO.getAerolinea());
        vuelo.setOrigen(vueloDTO.getOrigen());
        vuelo.setDestino(vueloDTO.getDestino());
        vuelo.setPrecio(vueloDTO.getPrecio());
        vuelo.setFechaHoraSalida(vueloDTO.getFechaHoraSalida());
        vuelo.setNumEscalas(vueloDTO.getNumEscalas());
        vuelo.setModeloAvion(vueloDTO.getModeloAvion());
        vuelo.setClaseServicio(vueloDTO.getClaseServicio());
        vuelo.setEquipaje(vueloDTO.isEquipaje());
        return vuelo;
    }
}
