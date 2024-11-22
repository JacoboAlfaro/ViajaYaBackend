package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.repositories.IPaqueteTuristicoRepository;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IPaqueteTuristicoService;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteTuristicoService implements IPaqueteTuristicoService {

    @Autowired
    IPaqueteTuristicoRepository paqueteTuristicoRepository;
    @Autowired
    IHotelService hotelService;
    @Autowired
    IVueloService vueloService;
    @Autowired
    IMapper<PaqueteTuristicoDTO, PaqueteTuristicoModel> mapper;


    public List<PaqueteTuristicoModel> findPaqueteTuristicoByPrecio(float precioMin, float precioMax){
        return paqueteTuristicoRepository.findPaqueteTuristicoByPrecio(precioMin, precioMax);
    }

    public List<PaqueteTuristicoModel> findPaqueteTuristicoByFecha(LocalDate fechaSalida){
        return paqueteTuristicoRepository.findPaqueteTuristicoByFecha(fechaSalida.getYear(), fechaSalida.getMonth().getValue(), fechaSalida.getDayOfMonth());
    }

    public List<PaqueteTuristicoModel> findPaqueteTuristicoByPrecioAndFechaSalida(float precioMin, float precioMax, LocalDate fechaSalida){
        return paqueteTuristicoRepository.findPaqueteTuristicoByPrecioAndFechaSalida(precioMin, precioMax, fechaSalida.getYear(), fechaSalida.getMonth().getValue(), fechaSalida.getDayOfMonth());
    }

    @Override
    public ArrayList<PaqueteTuristicoModel> getPaquetes(){
         return (ArrayList<PaqueteTuristicoModel>) paqueteTuristicoRepository.findAll();
    }

    @Override
    public Optional<PaqueteTuristicoModel> getByid(Long id){
        Optional<PaqueteTuristicoModel> paquete = paqueteTuristicoRepository.findById(id);
        if(paquete.isEmpty()){
            throw new BusinessException("Paquete turistico con id " + id + " no encontrado");
        }
        return paquete;
    }

    @Override
    public PaqueteTuristicoModel savePaqueteTuristico(PaqueteTuristicoDTO dto){
        PaqueteTuristicoModel paquete = mapper.toEntity(dto);
        return paqueteTuristicoRepository.save(paquete);
    }

    @Override
    public PaqueteTuristicoModel updateById(PaqueteTuristicoDTO request, Long id){
        PaqueteTuristicoModel paqueteExistente = paqueteTuristicoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Paquete turístico con id " + id + " no encontrado"));

        PaqueteTuristicoModel paqueteDTO = mapper.toEntity(request);

        paqueteExistente.setNombrePaquete(paqueteDTO.getNombrePaquete());
        paqueteExistente.setDestino(paqueteDTO.getDestino());
        paqueteExistente.setPrecio(paqueteDTO.getPrecio());
        paqueteExistente.setServiciosIncluidos(paqueteDTO.getServiciosIncluidos());
        paqueteExistente.setFechaSalida(paqueteDTO.getFechaSalida());

        if (request.getIdVuelo() != null) {
            VueloModel vuelo = vueloService.getVueloById(request.getIdVuelo())
                    .orElseThrow(() -> new BusinessException("Vuelo no encontrado"));
            paqueteExistente.setVuelo(vuelo);
        }
        if (request.getIdHotel() != null) {
            HotelModel hotel = hotelService.getHotelId(request.getIdHotel())
                    .orElseThrow(() -> new BusinessException("Hotel no encontrado"));
            paqueteExistente.setHotel(hotel);
        }

        return paqueteTuristicoRepository.save(paqueteExistente);
    }

    @Override
    public boolean deletePaquete(Long id){
        Optional<PaqueteTuristicoModel> paquete = paqueteTuristicoRepository.findById(id);
        if(paquete.isEmpty()){
            throw new BusinessException("Paquete turistico con id " + id + " no encontrado");
        }
        paqueteTuristicoRepository.deleteById(id);
        return true;
    }

    @Override
    public List<PaqueteTuristicoModel> getPaqueteById(List<Long> id) {
        List<PaqueteTuristicoModel> paquetes = paqueteTuristicoRepository.findByIdIn(id);

        if (paquetes.isEmpty()){
            throw new BusinessException("Paquete no encontrado con id " + id);
        }
        return paquetes;
    }
}
