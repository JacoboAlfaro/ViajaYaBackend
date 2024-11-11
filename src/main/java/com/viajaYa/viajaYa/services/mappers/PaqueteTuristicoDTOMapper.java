package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaqueteTuristicoDTOMapper implements IMapper<PaqueteTuristicoDTO, PaqueteTuristicoModel> {

    @Autowired
    private IVueloService vueloService;

    @Autowired
    private IHotelService hotelService;

    @Override
    public PaqueteTuristicoModel toEntity(PaqueteTuristicoDTO dto) {
        PaqueteTuristicoModel paquete = new PaqueteTuristicoModel();
        paquete.setNombrePaquete(dto.getNombrePaquete());
        paquete.setDestino(dto.getDestino());
        paquete.setPrecio(dto.getPrecio());
        paquete.setServiciosIncluidos(dto.getServiciosIncluidos());
        paquete.setFechaSalida(dto.getFechaSalida());

        asignarVueloYHotel(dto.getIdVuelo(), dto.getIdHotel(), paquete);


        return paquete;
    }

    @Override
    public PaqueteTuristicoDTO toDto(PaqueteTuristicoModel model) {
        PaqueteTuristicoDTO dto = new PaqueteTuristicoDTO();
        dto.setNombrePaquete(model.getNombrePaquete());
        dto.setDestino(model.getDestino());
        dto.setPrecio(model.getPrecio());
        dto.setServiciosIncluidos(model.getServiciosIncluidos());
        dto.setFechaSalida(model.getFechaSalida());
        if (model.getVuelo() != null) {
            dto.setIdVuelo(model.getVuelo().getId());
        }
        if (model.getHotel() != null) {
            dto.setIdHotel(model.getHotel().getId());
        }
        return dto;
    }

    //Aplicar este metodo cunaod se tenga una llave foranea dentro del modelo
    private void asignarVueloYHotel(Long idVuelo, Long idHotel, PaqueteTuristicoModel paquete) {
        if (idVuelo != null) {
            VueloModel vuelo = vueloService.getVueloById(idVuelo)
                    .orElseThrow(() -> new BusinessException("Vuelo no encontrado"));
            paquete.setVuelo(vuelo);
        }

        if (idHotel != null) {
            HotelModel hotel = hotelService.getHotelId(idHotel)
                    .orElseThrow(() -> new BusinessException("Hotel no encontrado"));
            paquete.setHotel(hotel);
        }
    }
}
