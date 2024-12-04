package com.viajaYa.viajaYa;

import com.viajaYa.viajaYa.services.patterns.singleton.GlobalAppConfigSingleton;
import com.viajaYa.viajaYa.services.patterns.singleton.Productos;
import com.viajaYa.viajaYa.repositories.IProductoRepository;
import com.viajaYa.viajaYa.utils.ApplicationLogger;
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

        ApplicationLogger.getInstance();
        System.out.println("Logger inicializado");

        GlobalAppConfigSingleton.getInstance();
        System.out.println("Configuración global inicializada");
        //INICIALIZAR LOS SINGLETONS AQUI
    }
}
