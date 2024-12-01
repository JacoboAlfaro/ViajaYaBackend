package com.viajaYa.viajaYa.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.viajaYa.viajaYa.models.enums.TipoProdcuto;
import com.viajaYa.viajaYa.repositories.IServicioAdicionalRepository;
import com.viajaYa.viajaYa.services.interfaces.IProductoService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;
import com.viajaYa.viajaYa.repositories.IVueloRepository;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;

@Service
public class VueloService implements IVueloService, IProductoServicioService<VueloDTO> {

    @Autowired
    IVueloRepository vueloRepository;

    @Autowired
    IServicioAdicionalRepository servicioAdicionalRepository;

    @Autowired
    IMapper<VueloDTO, VueloModel> mapper;

    @Autowired
    IProductoService productoService;


    
    public List<VueloModel> findVueloByPrecio(double precioMin, double precioMax){
        return vueloRepository.findVueloByPrecio(precioMin , precioMax);
    }

    
    public List<VueloModel> findVueloByFecha(LocalDateTime fechaHoraSalida){
        return vueloRepository.findVueloByFecha(fechaHoraSalida.getYear(), fechaHoraSalida.getMonth().getValue(), fechaHoraSalida.getDayOfMonth());
    }

    
    public List<VueloModel> findVueloByPrecioAndFecha(double precioMin,double precioMax, LocalDateTime fechaHoraSalida){
        return vueloRepository.findVueloByPrecioAndFecha(precioMin, precioMax,fechaHoraSalida.getYear(), fechaHoraSalida.getMonth().getValue(), fechaHoraSalida.getDayOfMonth());
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
        productoService.eliminarReseniasProducto(productoService.getIdProductos(TipoProdcuto.vuelos.name()), id);
        vueloRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public VueloDTO addServicioAdicional(Long idVuelo, Long idServicio){
        VueloModel vuelo = obtenerProdcutoConServicio(idVuelo, idServicio);

        if (vuelo.getServiciosAdicionales().contains(servicioAdicionalRepository.getReferenceById(idServicio))) {
            throw new BusinessException("El servicio adicional con id " + idServicio + " ya se encuentra en el vuelo");
        }
        vuelo.getServiciosAdicionales().add(servicioAdicionalRepository.getReferenceById(idServicio));

        VueloModel vueloNuevo = vueloRepository.save(vuelo);
        return mapper.toDto(vueloNuevo);
    }

    @Override
    @Transactional
    public VueloDTO removeServicioAdicional(Long idVuelo, Long idServicio) {
        VueloModel vuelo = obtenerProdcutoConServicio(idVuelo, idServicio);

        if (!vuelo.getServiciosAdicionales().contains(servicioAdicionalRepository.getReferenceById(idServicio))) {
            throw new BusinessException("El servicio adicional con id " + idServicio + " no está asociado al vuelo");
        }
        vuelo.getServiciosAdicionales().remove(servicioAdicionalRepository.getReferenceById(idServicio));

        VueloModel vueloNuevo = vueloRepository.save(vuelo);
        return mapper.toDto(vueloNuevo);
    }

    /*Metodo privado para obtener un paquete turistico con un servicio adicional
    [Aplicacion de los principio DRY Y KISS] */
    private VueloModel obtenerProdcutoConServicio(Long idVuelo, Long idServicio) {
        VueloModel vuelo = vueloRepository.findById(idVuelo)
                .orElseThrow(() -> new BusinessException("Vuelo con id " + idVuelo + " no encontrado"));

        if (!servicioAdicionalRepository.existsById(idServicio)) {
            throw new BusinessException("Servicio adicional con id " + idServicio + " no encontrado");
        }
        return vuelo;
    }

    public List<VueloModel> getVueloById(List<Long> id) {
        List<VueloModel> vuelos = vueloRepository.findByIdIn(id);
        if(vuelos.isEmpty()){
            throw new BusinessException("Vuelo no encontrado con id " + id);
        }
        return vuelos;
    }
}
