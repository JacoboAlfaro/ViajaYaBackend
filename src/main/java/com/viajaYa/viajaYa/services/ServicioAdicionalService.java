package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;
import com.viajaYa.viajaYa.repositories.IServicioAdicionalRepository;
import com.viajaYa.viajaYa.services.interfaces.IServicioAdicionalService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioAdicionalService implements IServicioAdicionalService {

    @Autowired
    private IServicioAdicionalRepository servicioAdicionalRepository;

    @Autowired
    private IMapper<ServicioAdicionalDTO, ServicioAdicionalModel> mapper;

    @Override
    public ArrayList<ServicioAdicionalDTO> getServicios() {
        ArrayList<ServicioAdicionalModel> servicios = (ArrayList<ServicioAdicionalModel>) servicioAdicionalRepository.findAll();
        return servicios.stream()
                .map(mapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<ServicioAdicionalDTO> getServicioById(Long id) {
        Optional<ServicioAdicionalModel> servicio = servicioAdicionalRepository.findById(id);
        if(servicio.isEmpty()){
            throw new BusinessException("Servicio adicional con id " + id + " no encontrado");
        }
        return servicio.map(mapper::toDto);
    }

    @Override
    public ServicioAdicionalModel saveServicio(ServicioAdicionalDTO dto) {
        ServicioAdicionalModel servicio = mapper.toEntity(dto);
        return servicioAdicionalRepository.save(servicio);
    }

    @Override
    public ServicioAdicionalModel updateServicioById(ServicioAdicionalDTO request, Long id) {
        ServicioAdicionalModel servicioExistente = servicioAdicionalRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Servicio adicional con id " + id + " no encontrado"));

        ServicioAdicionalModel servicioDTO = mapper.toEntity(request);

        servicioExistente.setNombreServicio(servicioDTO.getNombreServicio());
        servicioExistente.setDescripcion(servicioDTO.getDescripcion());
        servicioExistente.setTerminosCondiciones(servicioDTO.getTerminosCondiciones());
        servicioExistente.setPrecio(servicioDTO.getPrecio());
        servicioExistente.setCategoriaServicio(servicioDTO.getCategoriaServicio());

        return servicioAdicionalRepository.save(servicioExistente);
    }

    @Override
    @Transactional
    public boolean deleteServicioById(Long id) {
        Optional<ServicioAdicionalModel> servicio = servicioAdicionalRepository.findById(id);
        if(servicio.isEmpty()){
            throw new BusinessException("Servicio adicional con id " + id + " no encontrado");
        }
        servicioAdicionalRepository.deleteById(id);
        return true;
    }

    // DRY [Implementación de principio dry en el siguiente metodo]
    @Override
    public ArrayList<ServicioAdicionalDTO> getServiciosByRelacionId(String tipoProducto, Long relacionId) {
        ArrayList<ServicioAdicionalModel> servicios = switch (tipoProducto) {
            case "vuelos" -> servicioAdicionalRepository.findServiciosByVueloId(relacionId);
            case "hoteles" -> servicioAdicionalRepository.findServiciosByHotelId(relacionId);
            case "paqueteTuristicos" -> servicioAdicionalRepository.findServiciosByPaqueteId(relacionId);
            default -> throw new BusinessException("Tipo de producto no soportado: " + tipoProducto);
        };

        return servicios.stream()
                .map(mapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /*@Override
    public ArrayList<ServicioAdicionalDTO> getServiciosByRelacionId(String tipoProducto, Long relacionId) {
        ArrayList<ServicioAdicionalModel> servicios = servicioAdicionalRepository.findServiciosByRelacion(tipoProducto, relacionId);

        return servicios.stream()
                .map(mapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }*/
}
