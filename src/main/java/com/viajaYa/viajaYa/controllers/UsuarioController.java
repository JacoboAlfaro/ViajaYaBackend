package com.viajaYa.viajaYa.controllers;


import java.util.List;

import com.viajaYa.viajaYa.models.dtos.UsuarioRequestDTO;
import com.viajaYa.viajaYa.services.mappers.UsuarioRequestDTOMapper;
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
    private IUsuarioService usuarioService; //[Aplicando Polimorfismo]
    @Autowired
    private UsuarioRequestDTOMapper mapper;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<UsuarioRequestDTO>>> getUsuarios(){
        List<UsuarioModel> usuarios = this.usuarioService.getUsuarios();
        ApiResponse<List<UsuarioRequestDTO>> response = new ApiResponse<>(mapper.toDtoList(usuarios));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<UsuarioRequestDTO>> getUsuario(@PathVariable("id") Long id){
        UsuarioModel usuario = this.usuarioService.getUsuarioById(id).get();
        ApiResponse<UsuarioRequestDTO> response = new ApiResponse<>(mapper.toDto(usuario));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/porUsername/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<UsuarioRequestDTO>> getUsuarioByUsername(@PathVariable("username") String username){
        UsuarioModel usuario = this.usuarioService.getUsuarioByUsername(username).get();
        ApiResponse<UsuarioRequestDTO> response = new ApiResponse<>(mapper.toDto(usuario));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<UsuarioRequestDTO>> updateUsuario(@RequestBody UsuarioDTO usuario,
                                                                   @PathVariable("id") Long id){
        UsuarioModel usuarioActualizado = this.usuarioService.updateUsuarioById(usuario, id);
        ApiResponse<UsuarioRequestDTO> response = new ApiResponse<>(mapper.toDto(usuarioActualizado));
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
