package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.services.interfaces.IPaqueteTuristicoService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PaqueteTuristicoServiceTest {
    @Autowired
    private IPaqueteTuristicoService paqueteTuristicoService;

    private static Long idPaqueteValido = 1L;
    private static Long idPaqueteInvalido = -99L;

    @BeforeAll
    public static void setUp() {
        System.out.println("Init PaqueteTuristicoServiceTest");
    }

    @Test // 1
    public void testGetPaquetes() {
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoResponseDTO> paquetes = paqueteTuristicoService.getPaquetes();
            assertNotNull(paquetes);
            assertFalse(paquetes.isEmpty());
        });
        System.out.println("TEST: testGetPaquetes() superado");
    }

    @Test // 2
    public void testGetByIdValido() {
        assertDoesNotThrow(() -> {
            PaqueteTuristicoResponseDTO paquete = paqueteTuristicoService.getByid(idPaqueteValido).orElse(null);
            assertNotNull(paquete);
            assertEquals(idPaqueteValido, paquete.getId());
        });
        System.out.println("TEST: testGetByIdValido() superado");
    }

    @Test // 3
    public void testGetByIdInvalido() {
        assertThrows(BusinessException.class, () -> {
            paqueteTuristicoService.getByid(idPaqueteInvalido);
        });
        System.out.println("TEST: testGetByIdInvalido() superado");
    }

    @Test // 4
    public void testSavePaqueteTuristico() {
        PaqueteTuristicoDTO nuevoPaquete = new PaqueteTuristicoDTO();
        nuevoPaquete.setNombrePaquete("Paquete Prueba");
        nuevoPaquete.setDestino("Destino Prueba");
        nuevoPaquete.setPrecio(1000.0f);
        nuevoPaquete.setFechaSalida(LocalDate.now().plusDays(5));

        assertDoesNotThrow(() -> {
            PaqueteTuristicoModel paqueteGuardado = paqueteTuristicoService.savePaqueteTuristico(nuevoPaquete);
            assertNotNull(paqueteGuardado);
            assertEquals("Paquete Prueba", paqueteGuardado.getNombrePaquete());
        });
        System.out.println("TEST: testSavePaqueteTuristico() superado");
    }

    @Test // 5
    public void testUpdateByIdValido() {
        PaqueteTuristicoDTO paqueteActualizado = new PaqueteTuristicoDTO();
        paqueteActualizado.setNombrePaquete("Paquete Actualizado");
        paqueteActualizado.setDestino("Destino Actualizado");
        paqueteActualizado.setPrecio(2000.0f);
        paqueteActualizado.setFechaSalida(LocalDate.now().plusDays(10));

        assertDoesNotThrow(() -> {
            PaqueteTuristicoModel paquete = paqueteTuristicoService.updateById(paqueteActualizado, idPaqueteValido);
            assertNotNull(paquete);
            assertEquals("Paquete Actualizado", paquete.getNombrePaquete());
        });
        System.out.println("TEST: testUpdateByIdValido() superado");
    }

    @Test // 6
    public void testUpdateByIdInvalido() {
        PaqueteTuristicoDTO paqueteActualizado = new PaqueteTuristicoDTO();
        paqueteActualizado.setNombrePaquete("Paquete Invalido");

        assertThrows(BusinessException.class, () -> {
            paqueteTuristicoService.updateById(paqueteActualizado, idPaqueteInvalido);
        });
        System.out.println("TEST: testUpdateByIdInvalido() superado");
    }

    @Test // 7
    public void testDeletePaqueteValido() {
        assertDoesNotThrow(() -> {
            boolean resultado = paqueteTuristicoService.deletePaquete(idPaqueteValido);
            assertTrue(resultado);
        });
        System.out.println("TEST: testDeletePaqueteValido() superado");
    }

    @Test // 8
    public void testDeletePaqueteInvalido() {
        assertThrows(BusinessException.class, () -> {
            paqueteTuristicoService.deletePaquete(idPaqueteInvalido);
        });
        System.out.println("TEST: testDeletePaqueteInvalido() superado");
    }

    //Test de busquedas
    @Test // 9
    public void testFindPaqueteTuristicoByPrecioRangoValido() {
        float precioMin = 100.0f;
        float precioMax = 2000.0f;
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.findPaqueteTuristicoByPrecio(precioMin, precioMax);
            assertNotNull(paquetes);
            assertFalse(paquetes.isEmpty(), "No se encontraron paquetes en el rango de precio esperado");
            paquetes.forEach(paquete -> {
                assertTrue(paquete.getPrecio() >= precioMin && paquete.getPrecio() <= precioMax,
                        "El paquete con precio fuera del rango esperado");
            });
        });
        System.out.println("TEST: testFindPaqueteTuristicoByPrecioRangoValido() superado");
    }

    @Test // 10
    public void testFindPaqueteTuristicoByPrecioRangoInvalido() {
        float precioMin = 10000.0f;
        float precioMax = 20000.0f;
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.findPaqueteTuristicoByPrecio(precioMin, precioMax);
            assertNotNull(paquetes);
            assertTrue(paquetes.isEmpty(), "Se encontraron paquetes en un rango de precio inválido");
        });
        System.out.println("TEST: testFindPaqueteTuristicoByPrecioRangoInvalido() superado");
    }

    @Test //11
    public void testFindPaqueteTuristicoByFechaValida() {
        LocalDate fechaBusqueda = LocalDate.now().minusDays(31);
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.findPaqueteTuristicoByFecha(fechaBusqueda);
            assertNotNull(paquetes);
            assertFalse(paquetes.isEmpty(), "No se encontraron paquetes para la fecha esperada");
            paquetes.forEach(paquete -> {
                assertEquals(fechaBusqueda, paquete.getFechaSalida(), "La fecha del paquete no coincide con la buscada");
            });
        });
        System.out.println("TEST: testFindPaqueteTuristicoByFechaValida() superado");
    }

    @Test // 12
    public void testFindPaqueteTuristicoByFechaInvalida() {
        LocalDate fechaBusqueda = LocalDate.of(1900, 1, 1);
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.findPaqueteTuristicoByFecha(fechaBusqueda);
            assertNotNull(paquetes);
            assertTrue(paquetes.isEmpty(), "Se encontraron paquetes para una fecha inválida");
        });
        System.out.println("TEST: testFindPaqueteTuristicoByFechaInvalida() superado");
    }

    @Test // 13
    public void testFindPaqueteTuristicoByPrecioYFechaValido() {
        float precioMin = 500.0f;
        float precioMax = 2000.0f;
        LocalDate fechaBusqueda = LocalDate.now().minusDays(31);
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.findPaqueteTuristicoByPrecioAndFechaSalida(precioMin, precioMax, fechaBusqueda);
            assertNotNull(paquetes);
            assertFalse(paquetes.isEmpty(), "No se encontraron paquetes para el rango de precio y fecha esperados");
            paquetes.forEach(paquete -> {
                assertTrue(paquete.getPrecio() >= precioMin && paquete.getPrecio() <= precioMax,
                        "El paquete con precio fuera del rango esperado");
                assertEquals(fechaBusqueda, paquete.getFechaSalida(), "La fecha del paquete no coincide con la buscada");
            });
        });
        System.out.println("TEST: testFindPaqueteTuristicoByPrecioYFechaValido() superado");
    }

    @Test // 14
    public void testFindPaqueteTuristicoByPrecioYFechaInvalido() {
        float precioMin = 10000.0f;
        float precioMax = 20000.0f;
        LocalDate fechaBusqueda = LocalDate.of(1900, 1, 1);
        assertDoesNotThrow(() -> {
            List<PaqueteTuristicoModel> paquetes = paqueteTuristicoService.findPaqueteTuristicoByPrecioAndFechaSalida(precioMin, precioMax, fechaBusqueda);
            assertNotNull(paquetes);
            assertTrue(paquetes.isEmpty(), "Se encontraron paquetes para un rango de precio y fecha inválidos");
        });
        System.out.println("TEST: testFindPaqueteTuristicoByPrecioYFechaInvalido() superado");
    }
}
