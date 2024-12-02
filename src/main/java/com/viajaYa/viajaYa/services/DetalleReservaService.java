package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.DetalleReservaModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.DetalleReservaDTO;
import com.viajaYa.viajaYa.repositories.IDetalleReservaRepository;
import com.viajaYa.viajaYa.repositories.IReservaRepository;
import com.viajaYa.viajaYa.services.interfaces.IDetalleReservaService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

//[Aplicando principio experto de información]
@Service
public class DetalleReservaService implements IDetalleReservaService {

    @Autowired
    IDetalleReservaRepository detalleReservaRepository;

    @Autowired
    IReservaRepository reservaRepository;
    @Autowired
    IMapper<DetalleReservaDTO, DetalleReservaModel> mapper;

    //[Aplicando principio Alta cohesion y bajo acoplamiento]
    @Override
    public DetalleReservaModel saveDetalleReserva(DetalleReservaDTO dto) {
        if (dto.getIdReserva() == null) {
            throw new BusinessException("El id de la reserva no puede ser nulo");
        }
        if(dto.getIdProducto() < 1){
            throw new BusinessException("El id del producto no puede ser menor a 1");
        }
        if (dto.getIdProducto() > 3){
            throw new BusinessException("El id del producto no puede ser mayor a 3");
        }

        Optional<DetalleReservaModel> existente = detalleReservaRepository.findByReservaId(dto.getIdReserva());

        if(existente.isPresent()){
            throw new BusinessException("Ya existe un detalle de reserva para la reserva con ID " + dto.getIdReserva());
        }

        ReservaModel reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new BusinessException("La reserva con ID " + dto.getIdReserva() + " no existe"));

        DetalleReservaModel detalleReserva = mapper.toEntity(dto);
        detalleReserva.setReserva(reserva);
        return detalleReservaRepository.save(detalleReserva);
    }

    @Override
    public DetalleReservaModel getDetalleReservaByReserva(Long idReserva) {
        if (idReserva == null) {
            throw new BusinessException("El id de la reserva no puede ser nulo");
        }

        Optional<DetalleReservaModel> detalleReserva = detalleReservaRepository.findByReservaId(idReserva);
        if (detalleReserva.isEmpty()) {
            throw new BusinessException("No se encontró detalle de reserva con id de reserva " + idReserva);
        }

        return detalleReserva.get();
    }
}
