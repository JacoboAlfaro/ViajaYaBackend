package com.viajaYa.viajaYa.services.mappers;

//[Se aplica el principio de segregación de interfaces]
public interface IMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
}
