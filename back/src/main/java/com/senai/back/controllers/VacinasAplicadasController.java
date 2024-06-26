package com.senai.back.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.back.dtos.vacinasAplicadas.VacinasAplicadasInput;
import com.senai.back.dtos.vacinasAplicadas.VacinasAplicadasOutput;
import com.senai.back.services.VacinasAplicadasService;

@RestController
@RequestMapping("/aplicacoes")
public class VacinasAplicadasController {
    @Autowired
    private VacinasAplicadasService service;

    @PostMapping("/{id}/usuarios")
    public VacinasAplicadasOutput create(@PathVariable Long id, @RequestBody VacinasAplicadasInput input) {
        VacinasAplicadasOutput vacinas = service.create(id, input); 

        return vacinas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinasAplicadasOutput> read(@PathVariable Long id){
        VacinasAplicadasOutput vacinas = service.read(id);
        if(vacinas == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(vacinas);

    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
