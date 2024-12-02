package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;

//[Aplicando segregación de interfaz ISP]
public interface IProductoServicioService<T> {
    T addServicioAdicional(Long idProducto, Long idServicio);
    T removeServicioAdicional(Long idProducto, Long idServicio);
}
