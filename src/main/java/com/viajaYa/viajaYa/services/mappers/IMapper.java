package com.viajaYa.viajaYa.services.mappers;

public interface IMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
}
