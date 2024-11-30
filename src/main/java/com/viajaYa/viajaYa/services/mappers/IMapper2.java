package com.viajaYa.viajaYa.services.mappers;

import java.util.List;

public interface IMapper2<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<D> toDtoList(List<E> entities);
}
