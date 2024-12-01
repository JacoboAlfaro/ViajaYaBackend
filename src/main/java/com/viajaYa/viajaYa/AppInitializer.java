package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.models.singletons.Productos;
import com.viajaYa.viajaYa.repositories.IProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {

    // [Aplica principio de inversión de dependencias DIP]
    private final IProductoRepository productoRepository;

    public AppInitializer(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostConstruct
    public void init() {
        // [Aplica patrón SINGLETON]
        Productos productos = Productos.getInstance(productoRepository);
        System.out.println("Productos cargados: " + productos.getProductosValidos());

        //INICIALIZAR LOS SINGLETONS AQUI
    }
}
