package com.viajaYa.viajaYa.services.interfaces;

import java.util.ArrayList;
import java.util.Optional;

import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;

public interface IUsuarioService {
    public ArrayList<UsuarioModel> getUsuarios();
    public UsuarioModel saveUsuario(UsuarioDTO usuario);
    public Optional<UsuarioModel> getUsuarioById(Long id);
    public UsuarioModel updateUsuarioById(UsuarioDTO request,Long id);
    public boolean deleteUsuarioById(Long id);
}
