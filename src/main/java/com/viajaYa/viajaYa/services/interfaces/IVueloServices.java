package com.viajaYa.viajaYa.services.interfaces;
import java.util.ArrayList;
import java.util.Optional;

import com.viajaYa.viajaYa.models.VueloModel;

public interface IVueloServices {
    public ArrayList<VueloModel> getVuelos();
    public VueloModel saveVuelo(VueloModel vuelo);
    public Optional<VueloModel> getVueloById(Long id);
    public VueloModel updateVueloById(VueloModel request,Long id);
    public boolean deleteVueloById(Long id);

}
