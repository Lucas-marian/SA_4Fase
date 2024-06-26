package com.senai.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.back.dtos.users.profissional.ProfissionalInput;
import com.senai.back.dtos.users.profissional.ProfissionalOutput;
import com.senai.back.dtos.users.profissional.UpdateProfissionalDTO;
import com.senai.back.services.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
@Validated
public class ProfissionalController {
    @Autowired
    private ProfissionalService service;
    
    @GetMapping
    public ResponseEntity<List<ProfissionalOutput>> list(){
        List<ProfissionalOutput> list = service.list();

        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<ProfissionalOutput> create(@RequestBody ProfissionalInput users){
        ProfissionalOutput output = service.create(users);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalOutput> read(@PathVariable Long id){
        ProfissionalOutput users = service.read(id);

        if(users == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(users);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalOutput> update(@PathVariable Long id, @RequestBody UpdateProfissionalDTO input){
        ProfissionalOutput output = service.update(id, input);

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
