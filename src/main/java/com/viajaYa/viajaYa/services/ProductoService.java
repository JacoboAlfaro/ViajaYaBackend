package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.repositories.IProductoRepository;
import com.viajaYa.viajaYa.repositories.IReseniaRepository;
import com.viajaYa.viajaYa.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IReseniaRepository reseniaRepository;
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    // [Aplica principio OCP, SRP, DRY], [Patron FACADE (oculta funcionalidad)]
    public boolean eliminarReseniasProducto(Long IdProducto, Long IdReferencia) {
        List<ReseniaModel> resenias = reseniaRepository.findReseniaByProducto(IdProducto, IdReferencia);
        if(!resenias.isEmpty()){
            reseniaRepository.deleteAll(resenias);
        }
        return true;
    }

    @Override
    public Long getIdProductos(String tipoProducto){
        return productoRepository.findIdProductoByTipo(tipoProducto);
    }
}