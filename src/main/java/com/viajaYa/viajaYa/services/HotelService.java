package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.models.enums.TipoProdcuto;
import com.viajaYa.viajaYa.repositories.IHotelRepository;
import com.viajaYa.viajaYa.repositories.IServicioAdicionalRepository;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IProductoService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService, IProductoServicioService<HotelDTO> {

    @Autowired
    IHotelRepository hotelRepository;
    @Autowired
    IMapper<HotelDTO, HotelModel> mapper;
    @Autowired
    IServicioAdicionalRepository servicioAdicionalRepository;
    @Autowired
    IProductoService productoService;


    public List<HotelModel> findHotelByCiudad(String ciudad){
        return hotelRepository.findHotelByCiudad(ciudad);
    }

    public List<HotelModel> findHotelByPrecioNoche(float precioNocheMin, float precioNocheMax){
        return hotelRepository.findHotelByPrecioNoche(precioNocheMin, precioNocheMax);
    }

    public List<HotelModel> findHotelByCiudadAndPrecioNoche(String ciudad, float precioNocheMin, float precioNocheMax){
        return hotelRepository.findHotelByCiudadAndPrecioNoche(ciudad, precioNocheMin, precioNocheMax);
    }

    @Override
    public ArrayList<HotelModel> getHotel(){
        return (ArrayList<HotelModel>) hotelRepository.findAll();
    }

    @Override
    public HotelModel saveHotel(HotelDTO dto){
        HotelModel hotel = mapper.toEntity(dto);
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<HotelModel> getHotelId(Long id){
        Optional<HotelModel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty()){
            throw new BusinessException("Hotel con id " + id + " no encontrado");
        }
        return hotel;
    }

    @Override
    public HotelModel updateHotelId(HotelDTO request, Long id){
        HotelModel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Hotel con id " + id + " no encontrado"));

        hotel.setNombreHotel(request.getNombreHotel());
        hotel.setCiudad(request.getCiudad());
        hotel.setDireccion(request.getDireccion());
        hotel.setNumEstrellas(request.getNumEstrellas());
        hotel.setPais(request.getPais());
        hotel.setTipoHabitacion(request.getTipoHabitacion());
        hotel.setPrecioNoche(request.getPrecioNoche());

        hotelRepository.save(hotel);

        return hotel;
    }

    @Override
    public Boolean deleteHotelId(Long id){
        Optional<HotelModel> hotel = hotelRepository.findById(id);
        if(hotel.isEmpty()){
            throw new BusinessException("Hotel con id " + id + " no encontrado");
        }
        productoService.eliminarReseniasProducto(productoService.getIdProductos(TipoProdcuto.hoteles.name()), id);
        hotelRepository.deleteById(id);
        return true;
    }

    @Override
    public List<HotelModel> getHotelById(List<Long> id) {
        List<HotelModel> hoteles = hotelRepository.findByIdIn(id);

        if (hoteles.isEmpty()){
            throw new BusinessException("Hotel no encontrado con id " + id);
        }
        return hoteles;
    }

    @Override
    @Transactional
    public HotelDTO addServicioAdicional(Long idHotel, Long idServicio){
        HotelModel hotel = obtenerProdcutoConServicio(idHotel, idServicio);

        if (hotel.getServiciosAdicionales().contains(servicioAdicionalRepository.getReferenceById(idServicio))) {
            throw new BusinessException("El servicio adicional con id " + idServicio + " ya se encuentra en el hotel");
        }
        hotel.getServiciosAdicionales().add(servicioAdicionalRepository.getReferenceById(idServicio));

        HotelModel hotelNuevo = hotelRepository.save(hotel);
        return mapper.toDto(hotelNuevo);
    }

    @Override
    @Transactional
    public HotelDTO removeServicioAdicional(Long idHotel, Long idServicio) {
        HotelModel paquete = obtenerProdcutoConServicio(idHotel, idServicio);

        if (!paquete.getServiciosAdicionales().contains(servicioAdicionalRepository.getReferenceById(idServicio))) {
            throw new BusinessException("El servicio adicional con id " + idServicio + " no está asociado al hotel");
        }
        paquete.getServiciosAdicionales().remove(servicioAdicionalRepository.getReferenceById(idServicio));

        HotelModel hotelNuevo = hotelRepository.save(paquete);
        return mapper.toDto(hotelNuevo);
    }

    /*Metodo privado para obtener un paquete turistico con un servicio adicional
    [Aplicacion de los principio DRY Y KISS] */
    private HotelModel obtenerProdcutoConServicio(Long idHotel, Long idServicio) {
        HotelModel paquete = hotelRepository.findById(idHotel)
                .orElseThrow(() -> new BusinessException("Hotel con id " + idHotel + " no encontrado"));

        if (!servicioAdicionalRepository.existsById(idServicio)) {
            throw new BusinessException("Servicio adicional con id " + idServicio + " no encontrado");
        }
        return paquete;
    }

}
