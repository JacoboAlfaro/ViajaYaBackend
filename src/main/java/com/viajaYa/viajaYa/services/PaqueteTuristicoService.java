package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.repositories.IPaqueteTuristicoRepository;
import com.viajaYa.viajaYa.repositories.IServicioAdicionalRepository;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IPaqueteTuristicoService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteTuristicoService implements IPaqueteTuristicoService, IProductoServicioService<PaqueteTuristicoResponseDTO> {

    @Autowired
    IPaqueteTuristicoRepository paqueteTuristicoRepository;
    @Autowired
    IServicioAdicionalRepository servicioAdicionalRepository;
    @Autowired
    IHotelService hotelService;
    @Autowired
    IVueloService vueloService;
    @Autowired
    IMapper<PaqueteTuristicoDTO, PaqueteTuristicoModel> mapper;
    @Autowired
    IMapper<PaqueteTuristicoResponseDTO, PaqueteTuristicoModel> responseMapper;

    @Override
    public ArrayList<PaqueteTuristicoResponseDTO> getPaquetes(){
        ArrayList<PaqueteTuristicoModel> paquetes = (ArrayList<PaqueteTuristicoModel>) paqueteTuristicoRepository.findAll();
        return paquetes.stream()
                .map(responseMapper::toDto)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public Optional<PaqueteTuristicoResponseDTO> getByid(Long id){
        Optional<PaqueteTuristicoModel> paquete = paqueteTuristicoRepository.findById(id);
        if(paquete.isEmpty()){
            throw new BusinessException("Paquete turistico con id " + id + " no encontrado");
        }
        return paquete.map(responseMapper::toDto);
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
    @Transactional
    public PaqueteTuristicoResponseDTO addServicioAdicional(Long idPaquete, Long idServicio){
        PaqueteTuristicoModel paquete = obtenerProdcutoConServicio(idPaquete, idServicio);

        if (paquete.getServiciosAdicionales().contains(servicioAdicionalRepository.getReferenceById(idServicio))) {
            throw new BusinessException("El servicio adicional con id " + idServicio + " ya se encuentra en el paquete turístico");
        }
        paquete.getServiciosAdicionales().add(servicioAdicionalRepository.getReferenceById(idServicio));

        PaqueteTuristicoModel paqueteNuevo = paqueteTuristicoRepository.save(paquete);
        return responseMapper.toDto(paqueteNuevo);
    }

    @Override
    @Transactional
    public PaqueteTuristicoResponseDTO removeServicioAdicional(Long idPaquete, Long idServicio) {
        PaqueteTuristicoModel paquete = obtenerProdcutoConServicio(idPaquete, idServicio);

        if (!paquete.getServiciosAdicionales().contains(servicioAdicionalRepository.getReferenceById(idServicio))) {
            throw new BusinessException("El servicio adicional con id " + idServicio + " no está asociado al paquete turístico");
        }
        paquete.getServiciosAdicionales().remove(servicioAdicionalRepository.getReferenceById(idServicio));

        PaqueteTuristicoModel paqueteNuevo = paqueteTuristicoRepository.save(paquete);
        return responseMapper.toDto(paqueteNuevo);
    }

    /*Metodo privado para obtener un paquete turistico con un servicio adicional
    [Aplicacion de los principio DRY Y KISS] */
    private PaqueteTuristicoModel obtenerProdcutoConServicio(Long idPaquete, Long idServicio) {
        PaqueteTuristicoModel paquete = paqueteTuristicoRepository.findById(idPaquete)
                .orElseThrow(() -> new BusinessException("Paquete turístico con id " + idPaquete + " no encontrado"));

        if (!servicioAdicionalRepository.existsById(idServicio)) {
            throw new BusinessException("Servicio adicional con id " + idServicio + " no encontrado");
        }
        return paquete;
    public List<PaqueteTuristicoModel> getPaqueteById(List<Long> id) {
        List<PaqueteTuristicoModel> paquetes = paqueteTuristicoRepository.findByIdIn(id);

        if (paquetes.isEmpty()){
            throw new BusinessException("Paquete no encontrado con id " + id);
        }
        return paquetes;
    }
}
