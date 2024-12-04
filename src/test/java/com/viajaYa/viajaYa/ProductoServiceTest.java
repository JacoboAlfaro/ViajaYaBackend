package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.repositories.IProductoRepository;
import com.viajaYa.viajaYa.repositories.IReseniaRepository;
import com.viajaYa.viajaYa.services.ProductoService;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IReseniaRepository reseniaRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProductoRepository productoRepository;

    private Long idProductoValido;
    private Long idProductoInvalido;

    private Long idReferenciaValida;

    @BeforeEach
    public void setUp() {
        // Configura los datos iniciales necesarios para las pruebas.
        ReseniaModel resenia = new ReseniaModel();
        resenia.setIdProducto(1);
        resenia.setIdReferencia(100);
        resenia.setFecha(LocalDateTime.now());
        resenia.setCalificacion(5);
        resenia.setComentario("Excelente");
        resenia.setUsuario(usuarioService.getUsuarioById(6L).get());
        reseniaRepository.save(resenia);

        idProductoValido = (long) resenia.getIdProducto();
        idReferenciaValida = (long) resenia.getIdReferencia();
        idProductoInvalido = -99L; // Un ID que no existe.
    }

    @Test // 1
    public void testEliminarReseniasProductoConResenias() {
        boolean resultado = productoService.eliminarReseniasProducto(idProductoValido, idReferenciaValida);

        assertTrue(resultado, "El resultado debe ser true");
        List<ReseniaModel> resenias = reseniaRepository.findReseniaByProducto(idProductoValido, idReferenciaValida);
        assertTrue(resenias.isEmpty(), "Las reseñas deben haber sido eliminadas");
    }

    @Test // 2
    public void testEliminarReseniasProductoSinResenias() {
        Long idReferenciaInvalida = 999L;

        boolean resultado = productoService.eliminarReseniasProducto(idProductoValido, idReferenciaInvalida);

        assertTrue(resultado, "El resultado debe ser true incluso si no hay reseñas");
    }

    @Test // 3
    public void testGetIdProductosValido() {
        String tipoProducto = "vuelos";
        Long idEsperado = 1L;

        Long resultado = productoService.getIdProductos(tipoProducto);

        assertNotNull(resultado, "El ID del producto no debe ser nulo");
        assertEquals(idEsperado, resultado, "El ID del producto debe coincidir con el esperado");
    }

    @Test // 4
    public void testGetIdProductosInvalido() {
        String tipoProducto = "inexistente";

        Long resultado = productoService.getIdProductos(tipoProducto);

        assertNull(resultado, "El ID del producto debe ser nulo para un tipo no existente");
    }

    @Test // 5
    public void testEliminarReseniasProductoInvalido() {
        assertDoesNotThrow(() -> productoService.eliminarReseniasProducto(idProductoInvalido, idReferenciaValida));
    }
}
