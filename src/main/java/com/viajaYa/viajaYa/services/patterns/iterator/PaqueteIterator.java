package com.viajaYa.viajaYa.services.patterns.iterator;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;

import java.util.Iterator;
import java.util.List;

public class PaqueteIterator implements Iterator<PaqueteTuristicoModel> {
    private List<PaqueteTuristicoModel> paquetes;
    private int position = 0;

    public PaqueteIterator(List<PaqueteTuristicoModel> paquetes) {
        this.paquetes = paquetes;
    }

    @Override
    public boolean hasNext() {
        return position < paquetes.size();
    }

    @Override
    public PaqueteTuristicoModel next() {
        if (!hasNext()) {
            throw new BusinessException("No hay más paquetes turísticos.");
        }
        return paquetes.get(position++);
    }
}
