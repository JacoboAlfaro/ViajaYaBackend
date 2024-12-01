package com.viajaYa.viajaYa.services.interfaces;

import java.util.Set;

public interface IProductoService {
    public boolean eliminarReseniasProducto(Long IdProducto, Long IdReferencia);
    public Long getIdProductos(String tipoProducto);
}
