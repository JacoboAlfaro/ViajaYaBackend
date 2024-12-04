package com.viajaYa.viajaYa.services.patterns.iterator;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;

import java.util.Iterator;
import java.util.List;

public class HotelIterator implements Iterator<HotelModel> {
    private List<HotelModel> hoteles;
    private int position = 0;

    public HotelIterator(List<HotelModel> hoteles) {
        this.hoteles = hoteles;
    }

    @Override
    public boolean hasNext() {
        return position < hoteles.size();
    }

    @Override
    public HotelModel next() {
        if (!hasNext()) {
            throw new BusinessException("No hay más hoteles.");
        }
        return hoteles.get(position++);
    }
}
