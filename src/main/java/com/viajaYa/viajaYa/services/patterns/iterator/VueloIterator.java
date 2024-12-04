package com.viajaYa.viajaYa.services.patterns.iterator;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;

import java.util.Iterator;
import java.util.List;

public class VueloIterator implements Iterator<VueloModel> {
    private List<VueloModel> vuelos;
    private int position = 0;

    public VueloIterator(List<VueloModel> vuelos) {
        this.vuelos = vuelos;
    }

    @Override
    public boolean hasNext() {
        return position < vuelos.size();
    }

    @Override
    public VueloModel next() {
        if (!hasNext()) {
            throw new BusinessException("No hay más vuelos.");
        }
        return vuelos.get(position++);
    }
}
