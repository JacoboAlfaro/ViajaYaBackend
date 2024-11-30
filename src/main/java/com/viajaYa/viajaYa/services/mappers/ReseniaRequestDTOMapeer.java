package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.models.dtos.ReseniaRequestDTO;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReseniaRequestDTOMapeer implements IMapper<ReseniaRequestDTO, ReseniaModel> {

    @Autowired
    IUsuarioService usuarioService;
    @Override
    public ReseniaModel toEntity(ReseniaRequestDTO dto) {
        ReseniaModel resenia = new ReseniaModel();
        resenia.setId(dto.getId());
        resenia.setFecha(dto.getFecha());
        resenia.setCalificacion(dto.getCalificacion());
        resenia.setComentario(dto.getComentario());
        resenia.setIdProducto(dto.getIdProducto());
        resenia.setIdReferencia(dto.getIdReferencia());
        resenia.setUsuario((usuarioService.getUsuarioById(dto.getIdUsuario()).get()));

        return resenia;
    }

    @Override
    public ReseniaRequestDTO toDto(ReseniaModel entity) {
        ReseniaRequestDTO resenia = new ReseniaRequestDTO();
        resenia.setId(entity.getId());
        resenia.setFecha(entity.getFecha());
        resenia.setCalificacion(entity.getCalificacion());
        resenia.setComentario(entity.getComentario());
        resenia.setIdProducto(entity.getIdProducto());
        resenia.setIdReferencia(entity.getIdReferencia());
        resenia.setIdUsuario(entity.getUsuario().getId());

        return resenia;
    }
}
