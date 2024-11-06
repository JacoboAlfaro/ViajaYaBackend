package com.viajaYa.viajaYa.servicios;

import com.viajaYa.viajaYa.modelos.PaqueteTuristico;

import java.util.ArrayList;
import java.util.Optional;

public interface IPaqueteTuristicoService {
    public ArrayList<PaqueteTuristico> getPaquetes();
    public PaqueteTuristico savePaqueteTuristico(PaqueteTuristico paquete);
    public Optional<PaqueteTuristico> getByid(int id);
    public PaqueteTuristico updateById(PaqueteTuristico request, int id);
    public boolean deletePaquete(int id);
}
