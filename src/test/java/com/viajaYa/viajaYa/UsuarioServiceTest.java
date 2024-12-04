package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import com.viajaYa.viajaYa.services.patterns.builder.UsuarioBuilder;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UsuarioServiceTest {

    @Autowired
    private IUsuarioService usuarioService;

    private Long usuarioValidoId = 5L;
    private Long usuarioInvalidoId = -99L;

    @BeforeAll
    public static void setUp() {
        System.out.println("Init UsuarioServiceTest");
    }

    // Tests para obtener usuarios
    @Test // 1
    public void testGetUsuarioByIdValido() {
        assertDoesNotThrow(() -> {
            assertNotNull(usuarioService.getUsuarioById(usuarioValidoId));
        });
        System.out.println("TEST: testGetUsuarioByIdValido() superado");
    }

    @Test // 2
    public void testGetUsuarioByIdNoEncontrado() {
        assertThrows(BusinessException.class, () -> {
            usuarioService.getUsuarioById(usuarioInvalidoId);
        });
        System.out.println("TEST: testGetUsuarioByIdNoEncontrado() superado");
    }

    // Tests para actualizar usuarios
    @Test // 3
    public void testUpdateUsuarioByIdValido() {
        UsuarioDTO usuarioDTO = new UsuarioBuilder()
                .setNombre("Nombre Actualizado")
                .setIdentificacion("123456789")
                .setDireccion("Nueva Dirección")
                .setCorreoElectronico("correo@test.com")
                .setRol(0)
                .build();

        assertDoesNotThrow(() -> {
            assertNotNull(usuarioService.updateUsuarioById(usuarioDTO, usuarioValidoId));
        });
        System.out.println("TEST: testUpdateUsuarioByIdValido() superado");
    }

    @Test // 4
    public void testUpdateUsuarioByIdNoEncontrado() {
        UsuarioDTO usuarioDTO = new UsuarioBuilder()
                .setNombre("Nombre Actualizado")
                .setIdentificacion("123456789")
                .setDireccion("Nueva Dirección")
                .setCorreoElectronico("correo@test.com")
                .setRol(0)
                .build();

        assertThrows(BusinessException.class, () -> {
            usuarioService.updateUsuarioById(usuarioDTO, usuarioInvalidoId);
        });
        System.out.println("TEST: testUpdateUsuarioByIdNoEncontrado() superado");
    }

    @Test // 5
    public void testUpdateUsuarioIdentificacionDuplicada() {
        UsuarioDTO usuarioDTO = new UsuarioBuilder()
                .setNombre("Nombre Actualizado")
                .setIdentificacion("13112") // Reemplazar con una identificación existente
                .setDireccion("Nueva Dirección")
                .setCorreoElectronico("correo@test.com")
                .setRol(0)
                .build();

        assertThrows(BusinessException.class, () -> {
            usuarioService.updateUsuarioById(usuarioDTO, usuarioValidoId);
        });
        System.out.println("TEST: testUpdateUsuarioIdentificacionDuplicada() superado");
    }

    // Tests para eliminar usuarios
    @Test // 6
    public void testDeleteUsuarioByIdValido() {
        assertDoesNotThrow(() -> {
            assertTrue(usuarioService.deleteUsuarioById(usuarioValidoId));
        });
        System.out.println("TEST: testDeleteUsuarioByIdValido() superado");
    }

    @Test // 7
    public void testDeleteUsuarioByIdNoEncontrado() {
        assertThrows(BusinessException.class, () -> {
            usuarioService.deleteUsuarioById(usuarioInvalidoId);
        });
        System.out.println("TEST: testDeleteUsuarioByIdNoEncontrado() superado");
    }

    // Tests para obtener usuario por username
    @Test // 8
    public void testGetUsuarioByUsernameValido() {
        String usernameValido = "admin1"; // Reemplazar con un username válido existente
        assertDoesNotThrow(() -> {
            assertNotNull(usuarioService.getUsuarioByUsername(usernameValido));
        });
        System.out.println("TEST: testGetUsuarioByUsernameValido() superado");
    }

    @Test // 9
    public void testGetUsuarioByUsernameNoEncontrado() {
        String usernameInvalido = "noexiste";
        assertThrows(BusinessException.class, () -> {
            usuarioService.getUsuarioByUsername(usernameInvalido);
        });
        System.out.println("TEST: testGetUsuarioByUsernameNoEncontrado() superado");
    }
}
