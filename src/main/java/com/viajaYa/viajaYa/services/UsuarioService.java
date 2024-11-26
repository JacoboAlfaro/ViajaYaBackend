package com.viajaYa.viajaYa.services;


import java.util.Optional;
import java.util.ArrayList;

import com.viajaYa.viajaYa.models.AuthUserModel;
import com.viajaYa.viajaYa.repositories.IAuthUserRepository;
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
    IAuthUserRepository authUserRepository;
    
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
    public Optional<UsuarioModel> getUsuarioByUsername(String username){
        Optional<AuthUserModel> authUser = authUserRepository.findByUsername(username);
        if(authUser.isEmpty()){
            throw new BusinessException("Usuario con username " + username + " no encontrado");
        }

        Optional<UsuarioModel> usuario = usuarioRepository.findById(authUser.get().getUsuario().getId());
        if(usuario.isEmpty()){
            throw new BusinessException("Usuario con id " + authUser.get().getUsuario().getId() + " no encontrado");
        }
        return usuario;
    }


    @Override
    public UsuarioModel updateUsuarioById(UsuarioDTO usuario, Long id){
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuario con id " + id + " no encontrado"));

        usuarioRepository.findByIdentificacion(usuario.getIdentificacion())
                .ifPresent(usuarioModel -> {
                    throw new BusinessException("Ya existe un usuario con la identificacion");
                });

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setIdentificacion(usuario.getIdentificacion());
        usuarioExistente.setDireccion(usuario.getDireccion());
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
