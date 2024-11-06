package com.viajaYa.viajaYa.controladores;

import com.viajaYa.viajaYa.modelos.PaqueteTuristico;
import com.viajaYa.viajaYa.servicios.IPaqueteTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/paqueteTuristico")
public class PaqueteTuristicoController {

    @Autowired
    private IPaqueteTuristicoService paqueteTuristicoService;

    @GetMapping
    public ArrayList<PaqueteTuristico> getPaquetesTuristicos(){
        return this.paqueteTuristicoService.getPaquetes();
    }

    @GetMapping(path = "/{id}")
    public Optional<PaqueteTuristico> getPaqueteTuristico(@PathVariable("id") int id){
        return this.paqueteTuristicoService.getByid(id);
    }

    @PostMapping
    public PaqueteTuristico savePaqueteTuristico(@RequestBody PaqueteTuristico paquete){
        return this.paqueteTuristicoService.savePaqueteTuristico(paquete);
    }

    @PutMapping(path = "/{id}")
    public PaqueteTuristico updatePaqueteTuristico(@RequestBody PaqueteTuristico request,
                                                   @PathVariable("id") int id){
        return this.paqueteTuristicoService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deletePaqueteTuristico(@PathVariable("id") int id){
        boolean respuesta = this.paqueteTuristicoService.deletePaquete(id);
        if(respuesta){
            return "Se elimino el paquete turistico con id: " + id;
        } else{
            return "ERROR: No se pudo eliminar el paquete turistico con id: " + id;
        }
    }
}
