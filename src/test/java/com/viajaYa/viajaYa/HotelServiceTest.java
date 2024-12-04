package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.services.HotelService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    private Long idHotelValido;
    private Long idHotelInvalido = -99L;

    @BeforeEach
    public void setUp() {
        // Insertar datos en la base de datos para pruebas.
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setNombreHotel("Hotel Prueba");
        hotelDTO.setCiudad("Ciudad Test");
        hotelDTO.setDireccion("Dirección Test");
        hotelDTO.setNumEstrellas(5);
        hotelDTO.setPais("País Test");
        hotelDTO.setTipoHabitacion("Deluxe");
        hotelDTO.setPrecioNoche(200.0f);

        HotelModel hotel = hotelService.saveHotel(hotelDTO);
        idHotelValido = hotel.getId();
    }

    @Test // 1
    public void testGetHotelIdValido() {
        Optional<HotelModel> hotel = hotelService.getHotelId(idHotelValido);

        assertTrue(hotel.isPresent(), "El hotel con ID válido debe estar presente");
        assertEquals("Hotel Prueba", hotel.get().getNombreHotel(), "El nombre del hotel debe coincidir");
    }

    @Test // 2
    public void testGetHotelIdInvalido() {
        assertThrows(BusinessException.class, () -> {
            hotelService.getHotelId(idHotelInvalido);
        }, "Debe lanzar una excepción para un ID de hotel inválido");
    }

    @Test // 3
    public void testSaveHotel() {
        HotelDTO nuevoHotel = new HotelDTO();
        nuevoHotel.setNombreHotel("Nuevo Hotel");
        nuevoHotel.setCiudad("Ciudad Nueva");
        nuevoHotel.setDireccion("Nueva Dirección");
        nuevoHotel.setNumEstrellas(4);
        nuevoHotel.setPais("Nuevo País");
        nuevoHotel.setTipoHabitacion("Standard");
        nuevoHotel.setPrecioNoche(150.0f);

        HotelModel hotelGuardado = hotelService.saveHotel(nuevoHotel);

        assertNotNull(hotelGuardado, "El hotel guardado no debe ser nulo");
        assertEquals("Nuevo Hotel", hotelGuardado.getNombreHotel(), "El nombre del hotel guardado debe coincidir");
    }

    @Test // 4
    public void testUpdateHotelIdValido() {
        HotelDTO hotelActualizado = new HotelDTO();
        hotelActualizado.setNombreHotel("Hotel Actualizado");
        hotelActualizado.setCiudad("Ciudad Actualizada");
        hotelActualizado.setDireccion("Dirección Actualizada");
        hotelActualizado.setNumEstrellas(4);
        hotelActualizado.setPais("País Actualizado");
        hotelActualizado.setTipoHabitacion("Suite");
        hotelActualizado.setPrecioNoche(250.0f);

        HotelModel hotel = hotelService.updateHotelId(hotelActualizado, idHotelValido);

        assertNotNull(hotel, "El hotel actualizado no debe ser nulo");
        assertEquals("Hotel Actualizado", hotel.getNombreHotel(), "El nombre del hotel debe coincidir con el actualizado");
    }

    @Test // 5
    public void testDeleteHotelIdValido() {
        assertTrue(hotelService.deleteHotelId(idHotelValido), "Debe eliminar correctamente un hotel válido");
    }

    @Test // 6
    public void testDeleteHotelIdInvalido() {
        assertThrows(BusinessException.class, () -> {
            hotelService.deleteHotelId(idHotelInvalido);
        }, "Debe lanzar una excepción para intentar eliminar un hotel inexistente");
    }

    @Test // 7
    public void testFindHotelByCiudad() {
        List<HotelModel> hoteles = hotelService.findHotelByCiudad("Ciudad Test");

        assertNotNull(hoteles, "La lista de hoteles no debe ser nula");
        assertFalse(hoteles.isEmpty(), "Debe haber al menos un hotel en la ciudad");
        assertEquals("Ciudad Test", hoteles.get(0).getCiudad(), "La ciudad del hotel debe coincidir");
    }

    @Test // 8
    public void testFindHotelByPrecioNoche() {
        List<HotelModel> hoteles = hotelService.findHotelByPrecioNoche(100, 300);

        assertNotNull(hoteles, "La lista de hoteles no debe ser nula");
        assertFalse(hoteles.isEmpty(), "Debe haber al menos un hotel en el rango de precio");
    }

    @Test // 9
    public void testFindHotelByCiudadAndPrecioNoche() {
        List<HotelModel> hoteles = hotelService.findHotelByCiudadAndPrecioNoche("Ciudad Test", 100, 300);

        assertNotNull(hoteles, "La lista de hoteles no debe ser nula");
        assertFalse(hoteles.isEmpty(), "Debe haber al menos un hotel que coincida con la ciudad y el rango de precio");
        assertEquals("Ciudad Test", hoteles.get(0).getCiudad(), "La ciudad del hotel debe coincidir");
    }
}
