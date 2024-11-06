package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IPaqueteTuristicoService {
    public ArrayList<PaqueteTuristicoModel> getPaquetes();
    public PaqueteTuristicoModel savePaqueteTuristico(PaqueteTuristicoModel paquete);
    public Optional<PaqueteTuristicoModel> getByid(int id);
    public PaqueteTuristicoModel updateById(PaqueteTuristicoModel request, int id);
    public boolean deletePaquete(int id);
}
