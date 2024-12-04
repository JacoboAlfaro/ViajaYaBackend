package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.services.interfaces.IInformeService;
import com.viajaYa.viajaYa.services.patterns.facade.IApiResponseFacade;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/informe")
public class InformeController {

    IInformeService informeService; //[Aplicando Polimorfismo]
    IApiResponseFacade apiResponseFacade; //[Aplicando patron FACADE]

    public InformeController(IInformeService informeService,
                             IApiResponseFacade apiResponseFacade) {
        this.informeService = informeService;
        this.apiResponseFacade = apiResponseFacade;
    }

    @GetMapping("/{mes}/{anio}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> obtenerInforme(@PathVariable int mes, @PathVariable int anio) {
        Map<String, Object> informe = informeService.obtenerInformeMasVendidos(mes, anio);
        return ResponseEntity.ok(apiResponseFacade.success(informe));
    }
}
