package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.repositories.IInformeRepository;
import com.viajaYa.viajaYa.services.interfaces.IInformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformeService implements IInformeService {

    @Autowired
    IInformeRepository informeRepository;

    @Override
    public Map<String, Object> obtenerInformeMasVendidos(int mes) {
        List<Map<String, Object>> paquetes = informeRepository.findPaquetesMasVendidos(mes);
        List<Map<String, Object>> vuelos = informeRepository.findVuelosMasVendidos(mes);
        List<Map<String, Object>> hoteles = informeRepository.findHotelesMasVendidos(mes);

        Map<String, Object> informe = new HashMap<>();
        informe.put("paquetesTuristicos", paquetes);
        informe.put("vuelos", vuelos);
        informe.put("hoteles", hoteles);

        return informe;
    }
}
