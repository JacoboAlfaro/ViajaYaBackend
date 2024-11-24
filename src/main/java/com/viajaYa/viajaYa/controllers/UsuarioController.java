package com.viajaYa.viajaYa.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<UsuarioModel>>> getUsuarios(){
        List<UsuarioModel> usuarios = this.usuarioService.getUsuarios();
        ApiResponse<List<UsuarioModel>> response = new ApiResponse<>(usuarios);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<UsuarioModel>> getUsuario(@PathVariable("id") Long id){
        UsuarioModel usuario = this.usuarioService.getUsuarioById(id).get();
        ApiResponse<UsuarioModel> response = new ApiResponse<>(usuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<UsuarioModel>> updateUsuario(@RequestBody UsuarioDTO usuario,
                                                                                     @PathVariable("id") Long id){
        UsuarioModel usuarioActualizado = this.usuarioService.updateUsuarioById(usuario, id);
        ApiResponse<UsuarioModel> response = new ApiResponse<>(usuarioActualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<String>> deleteUsuario(@PathVariable("id") Long id){
        boolean respuesta = this.usuarioService.deleteUsuarioById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro el usuario con id " + id);
        return ResponseEntity.ok(response);
    }
}
