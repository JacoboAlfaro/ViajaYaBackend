package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;

public interface IProductoServicioService<T> {
    T addServicioAdicional(Long idProducto, Long idServicio);
    T removeServicioAdicional(Long idProducto, Long idServicio);
}
