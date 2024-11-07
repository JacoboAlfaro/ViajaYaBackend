package com.viajaYa.viajaYa.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.repositories.IVueloRepository;
import com.viajaYa.viajaYa.services.interfaces.IVueloServices;

@Service
public class VueloServices implements IVueloServices {
    @Autowired
    IVueloRepository vueloRepository;

    @Override
    public ArrayList<VueloModel> getVuelos(){
        return (ArrayList<VueloModel>) vueloRepository.findAll();
    }
    @Override
    public VueloModel saveVuelo(VueloModel vuelo){
        return vueloRepository.save(vuelo);
    }
    @Override
    public Optional<VueloModel> getVueloById(Long id){
        return vueloRepository.findById(id);
    }
    @Override
    public VueloModel updateVueloById(VueloModel request,Long id){
        VueloModel vuelo = vueloRepository.findById(id).get();

        vuelo.setAerolinea(request.getAerolinea());
        vuelo.setOrigen(request.getOrigen());
        vuelo.setDestino(request.getDestino());
        vuelo.setFechaHoraSalida(request.getFechaHoraSalida());
        vuelo.setPrecio(request.getPrecio());
        vuelo.setClaseServicio(request.getClaseServicio());
        vuelo.setNumEscalas(request.getNumEscalas());
        vuelo.setModeloAvion(request.getModeloAvion());
        vuelo.setNumVuelo(request.getNumVuelo());
        vuelo.setEquipaje(request.isEquipaje());
        return vuelo;
    }
    @Override
    public boolean deleteVueloById(Long id){
        try {
            vueloRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
