package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.ReseniaModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.models.dtos.ReseniaDTO;
import com.viajaYa.viajaYa.models.dtos.ReseniaRequestDTO;
import com.viajaYa.viajaYa.services.interfaces.IReseniaService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.services.mappers.IMapper2;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resenia")

public class ReseniaController {

    // [Aplica principio de inversión de dependencias DIP]
    IReseniaService reseniaService;
    IMapper2<ReseniaDTO, ReseniaModel> mapper;

    public ReseniaController(IReseniaService reseniaService, IMapper2<ReseniaDTO, ReseniaModel> mapper){
        this.reseniaService = reseniaService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ReseniaDTO>>> getHoteles(){
        ArrayList<ReseniaModel> resenias = this.reseniaService.getResenias();
        ApiResponse<List<ReseniaDTO>> response = new ApiResponse<>(mapper.toDtoList(resenias));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ReseniaDTO>> getHotelId(@PathVariable("id") Long id) {
        Optional<ReseniaModel> resenia = this.reseniaService.getReseniaById(id);
        ApiResponse<ReseniaDTO> response = new ApiResponse<>(mapper.toDto(resenia.get()));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/usuario/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ReseniaDTO>>> getReseniaByUsuario(@PathVariable("id") Long id){
        List<ReseniaModel> resenias = this.reseniaService.findReseniaByUsuario(id);
        ApiResponse<List<ReseniaDTO>> response = new ApiResponse<>(mapper.toDtoList(resenias));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/producto/{idProducto}/{idReferencia}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ReseniaDTO>>> getReseniaByProducto(@PathVariable("idProducto") Long idProducto,
                                                                              @PathVariable("idReferencia") Long idReferencia){
        List<ReseniaModel> resenias = this.reseniaService.findReseniaByProducto(idProducto, idReferencia);
        ApiResponse<List<ReseniaDTO>> response = new ApiResponse<>(mapper.toDtoList(resenias));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{usuarioId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ReseniaDTO>> createResenia(@RequestBody ReseniaRequestDTO resenia){
        ReseniaModel reseniaCreado = this.reseniaService.saveResenia(resenia);
        ApiResponse<ReseniaDTO> response = new ApiResponse<>(mapper.toDto(reseniaCreado));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ReseniaDTO>> updateResenia(@RequestBody ReseniaDTO resenia,
                                                               @PathVariable("id") Long id){
        ReseniaModel reseniaActualizado = this.reseniaService.updateReseniaById(resenia, id);
        ApiResponse<ReseniaDTO> response = new ApiResponse<>(mapper.toDto(reseniaActualizado));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<String>> deleteResenia(@PathVariable("id") Long id){
        this.reseniaService.deleteReseniaById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borró el paquete con id " + id);
        return ResponseEntity.ok(response);
    }
}
