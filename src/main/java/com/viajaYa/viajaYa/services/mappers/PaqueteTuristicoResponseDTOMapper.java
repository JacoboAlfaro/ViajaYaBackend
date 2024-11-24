package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaqueteTuristicoResponseDTOMapper implements IMapper<PaqueteTuristicoResponseDTO, PaqueteTuristicoModel> {

    @Autowired
    private IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalModel> servicioAdicionalDTOMapper;

    @Autowired
    private IVueloService vueloService;

    @Autowired
    private IHotelService hotelService;


    @Override
    public PaqueteTuristicoModel toEntity(PaqueteTuristicoResponseDTO dto) {
        PaqueteTuristicoModel paquete = new PaqueteTuristicoModel();
        paquete.setId(dto.getId());
        paquete.setNombrePaquete(dto.getNombrePaquete());
        paquete.setDestino(dto.getDestino());
        paquete.setPrecio(dto.getPrecio());
        paquete.setServiciosIncluidos(dto.getServiciosIncluidos());
        paquete.setFechaSalida(dto.getFechaSalida());

        asignarVueloYHotel(dto.getIdVuelo(), dto.getIdHotel(), paquete);


        return paquete;
    }

    @Override
    public PaqueteTuristicoResponseDTO toDto(PaqueteTuristicoModel model) {
        PaqueteTuristicoResponseDTO dto = new PaqueteTuristicoResponseDTO();
        dto.setId(model.getId());
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


        if(model.getServiciosAdicionales() != null){
            List<ServicioAdicionalRequestDTO> servicios = model.getServiciosAdicionales().stream()
                    .map(servicioAdicionalDTOMapper::toDto)
                    .collect(Collectors.toList());
            dto.setServiciosAdicionales(servicios);
        }else{
            dto.setServiciosAdicionales(new ArrayList<>());
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

    public List<PaqueteTuristicoResponseDTO> toDtoList(List<PaqueteTuristicoModel> paquetes) {
        return paquetes.stream()
                .map(this::toDto)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
