package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.ReseniaDTO;
import com.viajaYa.viajaYa.models.dtos.UsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReseniaDTOMapper implements IMapper2<ReseniaDTO, ReseniaModel> {

    @Autowired
    IMapper<UsuarioRequestDTO, UsuarioModel> userMapper;

    @Override
    public ReseniaModel toEntity(ReseniaDTO dto) {
        ReseniaModel resenia = new ReseniaModel();
        resenia.setId(dto.getId());
        resenia.setFecha(dto.getFecha());
        resenia.setCalificacion(dto.getCalificacion());
        resenia.setComentario(dto.getComentario());
        resenia.setIdProducto(dto.getIdProducto());
        resenia.setIdReferencia(dto.getIdReferencia());
        resenia.setUsuario(userMapper.toEntity(dto.getUsuario()));

        return resenia;
    }

    @Override
    public ReseniaDTO toDto(ReseniaModel entity) {
        ReseniaDTO resenia = new ReseniaDTO();
        resenia.setId(entity.getId());
        resenia.setFecha(entity.getFecha());
        resenia.setCalificacion(entity.getCalificacion());
        resenia.setComentario(entity.getComentario());
        resenia.setIdProducto(entity.getIdProducto());
        resenia.setIdReferencia(entity.getIdReferencia());
        resenia.setUsuario(userMapper.toDto(entity.getUsuario()));

        return resenia;
    }

    @Override
    public List<ReseniaDTO> toDtoList(List<ReseniaModel> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
