package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.services.interfaces.IPaqueteTuristicoService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/paqueteTuristico")
public class PaqueteTuristicoController {

    // [Aplica principio de inversión de dependencias DIP]
    private IPaqueteTuristicoService paqueteTuristicoService;
    private IProductoServicioService<PaqueteTuristicoResponseDTO> productoServicioService;
    private IMapper<PaqueteTuristicoResponseDTO, PaqueteTuristicoModel> mapper;

    public PaqueteTuristicoController(IPaqueteTuristicoService paqueteTuristicoService,
                                      IProductoServicioService<PaqueteTuristicoResponseDTO> productoServicioService,
                                      IMapper<PaqueteTuristicoResponseDTO, PaqueteTuristicoModel> mapper){
        this.paqueteTuristicoService = paqueteTuristicoService;
        this.productoServicioService = productoServicioService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/porprecio/{preciomin}/{preciomax}")
    public ResponseEntity<ApiResponse<List<PaqueteTuristicoModel>>> getPaqueteTuristicoByPrecio(@PathVariable("preciomin") float precioMin, @PathVariable("preciomax") float precioMax){
        List<PaqueteTuristicoModel> paquete = this.paqueteTuristicoService.findPaqueteTuristicoByPrecio(precioMin, precioMax);
        ApiResponse<List<PaqueteTuristicoModel>> response = new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/porfechasalida/{fechaSalida}")
    public ResponseEntity<ApiResponse<List<PaqueteTuristicoModel>>> getPaqueteTuristicoByFechaSalida(@PathVariable("fechaSalida") LocalDate fechaSalida){
        List<PaqueteTuristicoModel> paquete = this.paqueteTuristicoService.findPaqueteTuristicoByFecha(fechaSalida);
        ApiResponse<List<PaqueteTuristicoModel>> response = new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/porprecioyfechasalida/{preciomin}/{preciomax}/{fechaSalida}")
    public ResponseEntity<ApiResponse<List<PaqueteTuristicoModel>>> getPaqueteTuristicoByPrecioAndFechaSalida(@PathVariable("preciomin") float precioMin,@PathVariable("preciomax") float precioMax, @PathVariable("fechaSalida") LocalDate fechaSalida){
        List<PaqueteTuristicoModel> paquete = this.paqueteTuristicoService.findPaqueteTuristicoByPrecioAndFechaSalida(precioMin,precioMax,fechaSalida);
        ApiResponse<List<PaqueteTuristicoModel>> response = new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<PaqueteTuristicoResponseDTO>>> getPaquetesTuristicos(){
        List<PaqueteTuristicoResponseDTO> paquetes = this.paqueteTuristicoService.getPaquetes();
        ApiResponse<List<PaqueteTuristicoResponseDTO>> response = new ApiResponse<>(paquetes);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<PaqueteTuristicoResponseDTO>> getPaqueteTuristico(@PathVariable("id") Long id){
        PaqueteTuristicoResponseDTO paquete = this.paqueteTuristicoService.getByid(id).get();
        ApiResponse<PaqueteTuristicoResponseDTO> response = new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<PaqueteTuristicoResponseDTO>> savePaqueteTuristico(@RequestBody PaqueteTuristicoDTO dto){
        PaqueteTuristicoModel paquete = this.paqueteTuristicoService.savePaqueteTuristico(dto);
        ApiResponse<PaqueteTuristicoResponseDTO> response =  new ApiResponse<>(mapper.toDto(paquete));
        return ResponseEntity.ok(response);

    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<PaqueteTuristicoResponseDTO>> updatePaqueteTuristico(@RequestBody PaqueteTuristicoDTO request,
                                                                                     @PathVariable("id") Long id){
        PaqueteTuristicoModel paquete = this.paqueteTuristicoService.updateById(request, id);
        ApiResponse<PaqueteTuristicoResponseDTO> response = new ApiResponse<>(mapper.toDto(paquete));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<String>> deletePaqueteTuristico(@PathVariable("id") Long id){
        boolean respuesta = this.paqueteTuristicoService.deletePaquete(id);
        ApiResponse<String> response = new ApiResponse<>("Se borró el paquete con id " + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "addService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<PaqueteTuristicoResponseDTO>> addService(@PathVariable("idPaquete") Long idPaquete,
                                                                               @PathVariable("idServicio") Long idServicio){
        PaqueteTuristicoResponseDTO servicio = this.productoServicioService.addServicioAdicional(idPaquete, idServicio);
        ApiResponse<PaqueteTuristicoResponseDTO> response = new ApiResponse<>(servicio, "Se agrega el servicio con exito al paquete turistico");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "removeService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<PaqueteTuristicoResponseDTO>> removeService(@PathVariable("idPaquete") Long idPaquete,
                                                                                  @PathVariable("idServicio") Long idServicio){
        PaqueteTuristicoResponseDTO servicio = this.productoServicioService.removeServicioAdicional(idPaquete, idServicio);
        ApiResponse<PaqueteTuristicoResponseDTO> response = new ApiResponse<>(servicio, "Se elimina el servicio con exito del paquete turistico");
        return ResponseEntity.ok(response);
    }
}
