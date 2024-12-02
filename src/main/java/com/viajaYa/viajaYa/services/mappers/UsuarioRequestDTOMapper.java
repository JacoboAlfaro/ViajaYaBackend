package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioRequestDTO;
import com.viajaYa.viajaYa.repositories.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioRequestDTOMapper implements IMapper<UsuarioRequestDTO, UsuarioModel> {

    @Autowired
    private IAuthUserRepository authUserRepository;
    @Override
    public UsuarioModel toEntity(UsuarioRequestDTO dto) {
        UsuarioModel usuario = new UsuarioModel(); //[Aplicando principio Creador]
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setDireccion(dto.getDireccion());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setRol(dto.getRol());
        usuario.setAuthUser(authUserRepository.findByUsername(dto.getUsername()).get());

        return usuario;
    }

    @Override
    public UsuarioRequestDTO toDto(UsuarioModel entity) {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(); //[Aplicando principio Creador]
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setIdentificacion(entity.getIdentificacion());
        dto.setDireccion(entity.getDireccion());
        dto.setCorreoElectronico(entity.getCorreoElectronico());
        dto.setRol(entity.getRol());
        dto.setUsername(entity.getAuthUser().getUsername());

        return dto;
    }

    public List<UsuarioRequestDTO> toDtoList(List<UsuarioModel> paquetes) {
        return paquetes.stream()
                .map(this::toDto)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
