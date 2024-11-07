package com.viajaYa.viajaYa.controllers;


import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.services.interfaces.IVueloServices;

@RestController
@RequestMapping(path = "vuelos")
public class VueloController { 

    @Autowired
    private IVueloServices vueloServices;

    @GetMapping
    public ArrayList<VueloModel> getVuelos(){
        return this.vueloServices.getVuelos();
    }

    @PostMapping
    public VueloModel saveVuelo(@RequestBody VueloModel vuelo){
        return this.vueloServices.saveVuelo(vuelo);
    }

    @GetMapping(path = "/{id}")
    public Optional<VueloModel> getVueloById(@PathVariable("id") Long id){
        return this.vueloServices.getVueloById(id);
    }
    
    @PutMapping(path = "/{id}")
    public VueloModel updateVueloById(@RequestBody VueloModel request, @PathVariable("id") Long id){
        return this.vueloServices.updateVueloById(request, id);
    }

    @DeleteMapping(path = "/{id}")
        public String deleteVueloById(@PathVariable("id") Long id){
            boolean ok = this.vueloServices.deleteVueloById(id);
            if (ok){
                return "Se borro el vuelo con id " + id;
            } else {
                return "No se pudo borrar el vuelo con id " + id;
            }
        }
}
