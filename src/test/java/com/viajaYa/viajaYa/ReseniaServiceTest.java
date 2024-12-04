package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.models.dtos.ReseniaRequestDTO;
import com.viajaYa.viajaYa.services.interfaces.IReseniaService;
import com.viajaYa.viajaYa.services.patterns.builder.ReseniaRequestBuilder;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReseniaServiceTest {

    @Autowired
    private IReseniaService reseniaService;
    private Long idReseniaValido = 4L;
    private Long idReseniaInvalido = -99L;

    @BeforeAll
    public static void setUp() {
        System.out.println("Init ReseniaServiceTest");
    }
    @Test //1
    public void testFindReseniaByProductoInvalido() {
        assertThrows(BusinessException.class, () -> {
            reseniaService.findReseniaByProducto(-10L, 1L);
        });
        System.out.println("TEST: testFindReseniaByProductoInvalido() no valido superada");
    }

    @Test // 2
    public void testFindReseniaByProductoValido() {
        assertDoesNotThrow(() -> {
            reseniaService.findReseniaByProducto(1L, 1L);
        });
        System.out.println("TEST: testFindReseniaByProductoValido() valido superada");
    }

    //Tests para guardar una reseña
    @Test // 3
    public void testSaveReseniaValido() {
        ReseniaRequestDTO request = new ReseniaRequestBuilder()
                .id(1L)
                .calificacion(5)
                .comentario("Excelente")
                .fecha(LocalDateTime.now())
                .idProducto(1)
                .idReferencia(1)
                .idUsuario(6L) //Usuario existente
                .build();

        assertDoesNotThrow(() -> {
            reseniaService.saveResenia(request);
        });
        System.out.println("TEST: testSaveReseniaValido() valido superada");

    }

    @Test // 4
    public void testSaveReseniaUsuarioNoEncontrado() {
        ReseniaRequestDTO request = new ReseniaRequestBuilder()
                .id(1L)
                .calificacion(5)
                .comentario("Excelente")
                .fecha(LocalDateTime.now())
                .idProducto(1)
                .idReferencia(1)
                .idUsuario(99L) //Usuario no existente
                .build();

        assertThrows(BusinessException.class, () -> {
            reseniaService.saveResenia(request);
        });
        System.out.println("TEST: testSaveReseniaUsuarioNoEncontrado() valido superada");
    }

    @Test // 5
    public void testSaveReseniaCalificacionNoValida() {
        ReseniaRequestDTO request = new ReseniaRequestBuilder()
                .id(1L)
                .calificacion(10) //Solo puede estar entre 1 y 5
                .comentario("Excelente")
                .fecha(LocalDateTime.now())
                .idProducto(1)
                .idReferencia(1)
                .idUsuario(6L)
                .build();

        assertThrows(BusinessException.class, () -> {
            reseniaService.saveResenia(request);
        });
        System.out.println("TEST: testSaveReseniaCalificacionNoValida() valido superada");
    }

    //Tests para eliminar una reseña
    @Test // 6
    public void testDeleteReseniaValido() {
        assertTrue(reseniaService.deleteReseniaById(idReseniaValido)); //Reseña existente
        System.out.println("TEST: testDeleteReseniaValido() valido superada");
    }

    @Test // 7
    public void testDeleteReseniaNoEncontrado() {
        assertThrows(BusinessException.class, () -> {
            reseniaService.deleteReseniaById(idReseniaInvalido); //Reseña no existente
        });
        System.out.println("TEST: testDeleteReseniaNoEncontrado() no valido superada");
    }

    //Tests para obtener una reseña por id
    @Test // 8
    public void testGetReseniaByIdValido() {
        assertNotNull(reseniaService.getReseniaById(idReseniaValido));//Reseña existente
        System.out.println("TEST: testGetReseniaByIdValido() valido superada");

    }

    @Test // 9
    public void testGetReseniaByIdNoEncontrado() {
        assertThrows(BusinessException.class, () -> {
            reseniaService.getReseniaById(idReseniaInvalido); //Reseña no existente
        });
        System.out.println("TEST: testGetReseniaByIdNoEncontrado() no valido superada");
    }
}
