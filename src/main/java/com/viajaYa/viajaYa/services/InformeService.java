package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.repositories.IInformeRepository;
import com.viajaYa.viajaYa.services.interfaces.IInformeService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
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
    public Map<String, Object> obtenerInformeMasVendidos(int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new BusinessException("El mes debe estar entre 1 y 12");
        }
        if (anio < 2000) {
            throw new BusinessException("El año debe ser mayor a 2000");
        }
        List<Map<String, Object>> paquetes = informeRepository.findPaquetesMasVendidos(mes, anio);
        List<Map<String, Object>> vuelos = informeRepository.findVuelosMasVendidos(mes, anio);
        List<Map<String, Object>> hoteles = informeRepository.findHotelesMasVendidos(mes, anio);

        //[Se aplica el princiopio de creador]
        Map<String, Object> informe = new HashMap<>();
        informe.put("paquetesTuristicos", paquetes);
        informe.put("vuelos", vuelos);
        informe.put("hoteles", hoteles);

        return informe;
    }
}
