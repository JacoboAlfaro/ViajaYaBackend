package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.services.interfaces.IInformeService;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/informe")
public class InformeController {

    @Autowired
    IInformeService informeService;

    @GetMapping("/{mes}")
    public ResponseEntity<Map<String, Object>> obtenerInforme(@PathVariable int mes) {
        Map<String, Object> informe = informeService.obtenerInformeMasVendidos(mes);
        return ResponseEntity.ok(informe);
    }
}
