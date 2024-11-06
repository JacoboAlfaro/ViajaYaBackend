package com.viajaYa.viajaYa.servicios;

import com.viajaYa.viajaYa.modelos.PaqueteTuristico;
import com.viajaYa.viajaYa.repositorios.IPaqueteTuristicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PaqueteTuristicoService implements IPaqueteTuristicoService{

    @Autowired
    IPaqueteTuristicoRepository paqueteTuristicoRepository;

    @Override
    public ArrayList<PaqueteTuristico> getPaquetes(){
        return (ArrayList<PaqueteTuristico>) paqueteTuristicoRepository.findAll();
    }

    @Override
    public Optional<PaqueteTuristico> getByid(int id){
        return paqueteTuristicoRepository.findById(id);
    }

    @Override
    public PaqueteTuristico savePaqueteTuristico(PaqueteTuristico paquete){
        return paqueteTuristicoRepository.save(paquete);
    }

    @Override
    public PaqueteTuristico updateById(PaqueteTuristico request, int id){
        PaqueteTuristico paquete = paqueteTuristicoRepository.findById(id).get();
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
