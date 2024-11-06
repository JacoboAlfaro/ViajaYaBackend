package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.repositories.IPaqueteTuristicoRepository;
import com.viajaYa.viajaYa.services.interfaces.IPaqueteTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PaqueteTuristicoService implements IPaqueteTuristicoService {

    @Autowired
    IPaqueteTuristicoRepository paqueteTuristicoRepository;

    @Override
    public ArrayList<PaqueteTuristicoModel> getPaquetes(){
        return (ArrayList<PaqueteTuristicoModel>) paqueteTuristicoRepository.findAll();
    }

    @Override
    public Optional<PaqueteTuristicoModel> getByid(int id){
        return paqueteTuristicoRepository.findById(id);
    }

    @Override
    public PaqueteTuristicoModel savePaqueteTuristico(PaqueteTuristicoModel paquete){
        return paqueteTuristicoRepository.save(paquete);
    }

    @Override
    public PaqueteTuristicoModel updateById(PaqueteTuristicoModel request, int id){
        PaqueteTuristicoModel paquete = paqueteTuristicoRepository.findById(id).get();
        paquete.setNombrePaquete(request.getNombrePaquete());
        paquete.setDestino(request.getDestino());
        paquete.setPrecio(request.getPrecio());
        paquete.setServiciosIncluidos(request.getServiciosIncluidos());
        paquete.setFechaSalida(request.getFechaSalida());
        paquete.setIdVuelo(request.getIdVuelo());
        paquete.setIdHotel(request.getIdHotel());

        paqueteTuristicoRepository.save(paquete);

        return paquete;
    }

    @Override
    public boolean deletePaquete(int id){
        try{
            paqueteTuristicoRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
