package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.services.patterns.builder.UsuarioBuilder;
import org.springframework.stereotype.Component;

import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;

@Component
public class UsuarioDTOMapper implements IMapper<UsuarioDTO, UsuarioModel>{

    //[Aplicando principio DRY]
    @Override
    public UsuarioModel toEntity(UsuarioDTO dto) {
        UsuarioModel usuario = new UsuarioModel(); //[Aplicando principio Creador]
        usuario.setNombre(dto.getNombre());
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setDireccion(dto.getDireccion());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setRol(dto.getRol());
        return usuario;
    }

    @Override
    public UsuarioDTO toDto(UsuarioModel model) {
        UsuarioDTO dto = new UsuarioBuilder() //Patron Builder
                .setNombre(model.getNombre())
                .setIdentificacion(model.getIdentificacion())
                .setDireccion(model.getDireccion())
                .setCorreoElectronico(model.getCorreoElectronico())
                .setRol(model.getRol())
                .build();
        return dto;
    }
}
