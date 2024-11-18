package com.viajaYa.viajaYa.services;


import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;
import com.viajaYa.viajaYa.repositories.IUsuarioRepository;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    
    @Autowired
    IMapper<UsuarioDTO, UsuarioModel> mapper;

    @Override
    public ArrayList<UsuarioModel> getUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioModel> getUsuarioById(Long id){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new BusinessException("Usuario con id " + id + " no encontrado");
        }
        return usuario;
    } 

    @Override
    public UsuarioModel saveUsuario(UsuarioDTO dto){
        UsuarioModel usuario = mapper.toEntity(dto);
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioModel updateUsuarioById(UsuarioDTO usuario, Long id){
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuario con id " + id + " no encontrado"));

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setIdentificacion(usuario.getIdentificacion());
        usuarioExistente.setContrasena(usuario.getContrasena());
        usuarioExistente.setDireccion(usuario.getDireccion());
        usuarioExistente.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioExistente.setRol(usuario.getRol());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public boolean deleteUsuarioById(Long id){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new BusinessException("El Usuario con id " + id + " no encontrado");
        }
        usuarioRepository.deleteById(id);
        return true;
    }

}
