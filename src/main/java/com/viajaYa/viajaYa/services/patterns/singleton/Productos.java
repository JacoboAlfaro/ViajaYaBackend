package com.viajaYa.viajaYa.services.patterns.singleton;

import com.viajaYa.viajaYa.models.ProductoModel;
import com.viajaYa.viajaYa.repositories.IProductoRepository;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Productos {
    private static Productos instance;
    private final IProductoRepository productoRepository;
    private final Set<Long> productosValidos = new HashSet<>();

    private Productos(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        cargarProductos();
    }

    public static Productos getInstance(IProductoRepository productoRepository) {
        if (instance == null) {
            synchronized (Productos.class) {
                if (instance == null) {
                    instance = new Productos(productoRepository);
                }
            }
        }
        return instance;
    }

    private void cargarProductos() {
        List<ProductoModel> productos = productoRepository.findAll();
        for (ProductoModel producto : productos) {
            productosValidos.add(producto.getId());
        }
    }

    public boolean isValidProductoId(Long idProducto) {
        return productosValidos.contains(idProducto);
    }
}