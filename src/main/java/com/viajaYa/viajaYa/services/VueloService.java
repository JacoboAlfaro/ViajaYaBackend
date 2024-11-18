package com.viajaYa.viajaYa.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;
import com.viajaYa.viajaYa.repositories.IVueloRepository;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;

@Service
public class VueloService implements IVueloService {

    @Autowired
    IVueloRepository vueloRepository;

    @Autowired
    IMapper<VueloDTO, VueloModel> mapper;


    
    public VueloModel findVueloByPrecio(double precio){
        return vueloRepository.findVueloByPrecio(precio);
    }

    
    public VueloModel findVueloByFechaHoraSalida(LocalDateTime fechaHoraSalida){
        return vueloRepository.findVueloByFechaHoraSalida(fechaHoraSalida);
    }

    
    public VueloModel findVueloByPrecioAndFechaHoraSalida(double precio, LocalDateTime fechaHoraSalida){
        return vueloRepository.findVueloByPrecioAndFechaHoraSalida(precio, fechaHoraSalida);
    }

    @Override
    public ArrayList<VueloModel> getVuelos(){
        return (ArrayList<VueloModel>) vueloRepository.findAll();
    }
    @Override
    public Optional<VueloModel> getVueloById(Long id){
        Optional<VueloModel> vuelo = vueloRepository.findById(id);
        if(vuelo.isEmpty()){
            throw new BusinessException("Vuelo con id " + id + " no encontrado");
        }
        return vuelo;
    }
    @Override
    public VueloModel saveVuelo(VueloDTO dto){
        VueloModel vuelo = mapper.toEntity(dto);
        return vueloRepository.save(vuelo);
    }

    @Override
    public VueloModel updateVueloById(VueloDTO vuelo,Long id){
        VueloModel vueloExistente = vueloRepository.findById(id).
                orElseThrow(() -> new BusinessException("Vuelo con id " + id + " no encontrado"));

        vueloExistente.setAerolinea(vuelo.getAerolinea());
        vueloExistente.setOrigen(vuelo.getOrigen());
        vueloExistente.setDestino(vuelo.getDestino());
        vueloExistente.setFechaHoraSalida(vuelo.getFechaHoraSalida());
        vueloExistente.setPrecio(vuelo.getPrecio());
        vueloExistente.setClaseServicio(vuelo.getClaseServicio());
        vueloExistente.setNumEscalas(vuelo.getNumEscalas());
        vueloExistente.setModeloAvion(vuelo.getModeloAvion());
        vueloExistente.setNumVuelo(vuelo.getNumVuelo());
        vueloExistente.setEquipaje(vuelo.isEquipaje());

        return vueloRepository.save(vueloExistente);
    }
    @Override
    public boolean deleteVueloById(Long id){
        Optional<VueloModel> vuelo = vueloRepository.findById(id);
        if(vuelo.isEmpty()){
            throw new BusinessException("El vuelo con id " + id + " no encontrado");
        }
        vueloRepository.deleteById(id);
        return true;
    }
}
