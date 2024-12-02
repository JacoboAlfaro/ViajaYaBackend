package com.viajaYa.viajaYa.services.interfaces;


//[Aplicando segregación de interfaz ISP]
public interface IProductoService {
    public boolean eliminarReseniasProducto(Long IdProducto, Long IdReferencia);
    public Long getIdProductos(String tipoProducto);
}
