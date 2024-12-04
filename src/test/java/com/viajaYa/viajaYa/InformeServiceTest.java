package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.services.interfaces.IInformeService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InformeServiceTest {

    @Autowired
    private IInformeService informeService;

    @BeforeEach
    public void setUp() {
        System.out.println("Iniciando pruebas de InformeService...");
    }

    @Test // 1
    public void testObtenerInformeMasVendidosConDatosValidos() {
        int mes = 5;
        int anio = 2024;

        assertDoesNotThrow(() -> {
            Map<String, Object> informe = informeService.obtenerInformeMasVendidos(mes, anio);
            assertNotNull(informe, "El informe no debe ser nulo");
            assertTrue(informe.containsKey("paquetesTuristicos"), "El informe debe contener paquetes turísticos");
            assertTrue(informe.containsKey("vuelos"), "El informe debe contener vuelos");
            assertTrue(informe.containsKey("hoteles"), "El informe debe contener hoteles");
        });

        System.out.println("TEST: testObtenerInformeMasVendidosConDatosValidos() completado");
    }

    @Test // 2
    public void testObtenerInformeMasVendidosConMesInvalidoBajo() {
        int mes = 0; // Mes no valido
        int anio = 2024;

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            informeService.obtenerInformeMasVendidos(mes, anio);
        });

        assertEquals("El mes debe estar entre 1 y 12", exception.getMessage());
        System.out.println("TEST: testObtenerInformeMasVendidosConMesInvalidoBajo() completado");
    }

    @Test // 3
    public void testObtenerInformeMasVendidosConMesInvalidoAlto() {
        int mes = 13; // Mes no valido
        int anio = 2024;

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            informeService.obtenerInformeMasVendidos(mes, anio);
        });

        assertEquals("El mes debe estar entre 1 y 12", exception.getMessage());
        System.out.println("TEST: testObtenerInformeMasVendidosConMesInvalidoAlto() completado");
    }

    @Test // 4
    public void testObtenerInformeMasVendidosConAnioInvalido() {
        int mes = 5;
        int anio = 1999; // Año no valido

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            informeService.obtenerInformeMasVendidos(mes, anio);
        });

        assertEquals("El año debe ser mayor a 2000", exception.getMessage());
        System.out.println("TEST: testObtenerInformeMasVendidosConAnioInvalido() completado");
    }

    @Test // 5
    public void testObtenerInformeMasVendidosConDatosFrontera() {
        int mes = 1;
        int anio = 2000;

        assertDoesNotThrow(() -> {
            Map<String, Object> informe = informeService.obtenerInformeMasVendidos(mes, anio);
            assertNotNull(informe, "El informe no debe ser nulo");
        });

        System.out.println("TEST: testObtenerInformeMasVendidosConDatosFrontera() completado");
    }
}
