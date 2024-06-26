package com.senai.back.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.back.dtos.vacinas.VacinasInput;
import com.senai.back.dtos.vacinas.VacinasOutput;
import com.senai.back.services.VacinasService;


@RestController
@RequestMapping("/vacinas")
public class VacinasController {
    @Autowired
    private VacinasService service;
    
    @GetMapping
    public ResponseEntity<List<VacinasOutput>> list(){
        List<VacinasOutput> list = service.list();

        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<VacinasOutput> create(@RequestBody VacinasInput vacinas){
        VacinasOutput output = service.create(vacinas);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinasOutput> read(@PathVariable Long id){
        VacinasOutput vacinas = service.read(id);

        if(vacinas == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(vacinas);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VacinasOutput> update(@PathVariable Long id, @RequestBody VacinasInput input){
        VacinasOutput output = service.update(id, input);

        if(output == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(output);

    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
