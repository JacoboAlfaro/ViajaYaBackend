package com.viajaYa.viajaYa.services.mappers;

import org.springframework.stereotype.Component;

import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;

@Component
public class UsuarioDTOMapper implements IMapper<UsuarioDTO, UsuarioModel>{

    @Override
    public UsuarioModel toEntity(UsuarioDTO dto) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(dto.getNombre());
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setContraseña(dto.getContraseña());
        usuario.setDireccion(dto.getDireccion());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setRol(dto.getRol());
        return usuario;
    }

    @Override
    public UsuarioDTO toDto(UsuarioModel model) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(model.getNombre());
        dto.setIdentificacion(model.getIdentificacion());
        dto.setContraseña(model.getContraseña());
        dto.setDireccion(model.getDireccion());
        dto.setCorreoElectronico(model.getCorreoElectronico());
        dto.setRol(model.getRol());
        return dto;
    }
    
}
