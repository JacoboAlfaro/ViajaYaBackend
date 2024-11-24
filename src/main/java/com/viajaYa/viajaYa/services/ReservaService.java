package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.*;
import com.viajaYa.viajaYa.models.dtos.ReservaDTO;
import com.viajaYa.viajaYa.repositories.IReservaRepository;
import com.viajaYa.viajaYa.repositories.IUsuarioRepository;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.*;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    IReservaRepository reservaRepository;
    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    IVueloService vueloService;
    @Autowired
    IPaqueteTuristicoService paqueteTuristicoService;
    @Autowired
    IHotelService hotelService;
    @Autowired
    IMapper<ReservaDTO, ReservaModel> mapper;

    @Override
    public ArrayList<ReservaModel> getReservas() {
        return (ArrayList<ReservaModel>) reservaRepository.findAll();
    }

    @Override
    public ReservaModel saveReserva(ReservaDTO dto) {
        ReservaModel reserva = mapper.toEntity(dto);

        //Usuario con reserva
        if (dto.getIdUsuario() != null) {
            UsuarioModel usuario = usuarioService.getUsuarioById(dto.getIdUsuario())
                    .orElseThrow(() -> new BusinessException("Usuario no encontrado con id " + dto.getIdUsuario()));
            reserva.setUsuario(usuario);
        }

        //Vuelos con reserva
        if (dto.getIdVuelos() != null && !dto.getIdVuelos().isEmpty()) {
            List<VueloModel> vuelos = vueloService.getVueloById(dto.getIdVuelos());
            reserva.setVuelos(vuelos);
        }

        //PaquetesTuristicos con reserva
        if (dto.getIdPaquetes() != null && !dto.getIdPaquetes().isEmpty()) {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.getPaqueteById(dto.getIdPaquetes());
            reserva.setPaquetesTuristicos(paquetes);
        }

        //Hoteles con reserva
        if (dto.getIdHoteles() != null && !dto.getIdHoteles().isEmpty()) {
            List<HotelModel> hoteles = hotelService.getHotelById(dto.getIdHoteles());
            reserva.setHoteles(hoteles);
        }

        reserva.setConfirmada(false);

        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<ReservaModel> getReservaById(Long id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        if (reserva.isEmpty()) {
            throw new BusinessException("Reserva con id " + id + " no encontrado");
        }
        return reserva;
    }


    @Override
    public ReservaModel updateReservaById(ReservaDTO request, Long id) {
        ReservaModel reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Reserva con id " + id + " no encontrado"));


        ReservaModel reservaDTO = mapper.toEntity(request);

        reservaExistente.setEstado(reservaDTO.isEstado());
        reservaExistente.setUsuario(reservaDTO.getUsuario());

        if (request.getIdUsuario() != null) {
            UsuarioModel usuario = usuarioService.getUsuarioById(request.getIdUsuario())
                    .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
            reservaExistente.setUsuario(usuario);
        }
        return reservaRepository.save(reservaExistente);
    }

    @Override
    public Boolean deleteReservaById(Long id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        if (reserva.isEmpty()) {
            throw new BusinessException("Reserva con id " + id + " no encontrado");
        }
        reservaRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ReservaModel> getReservasByUsuario(Long idUsuario) {
        usuarioService.getUsuarioById(idUsuario)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado con id " + idUsuario));
        return reservaRepository.findByUsuarioId(idUsuario);
    }


    //Se aplica el princio OCP
    @Override
    public boolean confirmarReserva(Long id) {
        ReservaModel reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Reserva no encontrada con id " + id));

        if (reserva.isConfirmada()) {
            throw new BusinessException("La reserva con id " + id + " ya está confirmada.");
        }

        reserva.setConfirmada(true);
        reservaRepository.save(reserva);
        return true;
    }

    @Override
    public List<ReservaModel> getHistorialReservasByUsuario(Long idUsuario) {
        UsuarioModel usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado con id " + idUsuario));
        return reservaRepository.findByUsuario(usuario);
    }

}
